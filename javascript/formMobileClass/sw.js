const VERSION = 'v4';

// offline resource list
const APP_STATIC_RESOURCES = [
    'index.html',
    'style.css',
    'app.js',
    'vacationtracker.json',
    'images/icons/icon-512x512.png'
];

const CACHE_NAME = `vacation-tracker-$(VERSION)`;

/* handle the install event and retrieve and store the file listed for the cache */
self.addEventListener('install', (event) => {
    event.waitUntil(
        (async () => {
            const cache = await caches.open(CACHE_NAME);
            cache.addAll(APP_STATIC_RESOURCES);
        })()
    );
});

/* 
    use the activate event to delete any old caches so we don't run out of space.
    we're going to delete all but th ecurrent one, then set the service worker as
    the controller for our app (PWA)
*/
self.addEventListener("activate", (event) => {
    event.waitUntil(
        (async ()=>{
            // get the names of existing caches
            const names = await caches.keys();

            // iterate through the list and check each one to see if it's the current cache
            // delete any that aren't the current cache
            await Promise.all(
                names.map(name => {
                    if (name !== CACHE_NAME) {
                        return caches.delete(name);
                    }
                })
            ); // end Promise.all

            // use the claim() method of client's interface to enable our service worker as the controller
            await clients.claim();
        })()
    )
});

/* use the fetch even to intercept requests and respond with cached resources */
self.addEventListener('fetch', (event) => {
    event.respondWith(
        (async () => {
            // try to get the resource from the cache
            const cachedResponse = await caches.match(event.request);
            if (cachedResponse) {
                return cachedResponse;
            }

            // if the resource is not in the cache, get it from the network
            try {
                const networkResponse = await fetch(event.request);

                // cache the new response for future use
                cache.put(event.request, networkResponse.clone());

                // return the network response
                return networkResponse;

            } catch (error) {
                // if the network request fails, return a custom offline page
                console.error("Fetch failed; returning offline page instead.", error);

                // if the request is for a page, return index.html as a fallback
                if (event.request.mode === 'navigate') {
                    return caches.match('/index.html');
                }

                // for eveytyhing else, return an error
                throw error;
            }
        })()
    );
});

// // send a message to the client we will use to update the data
// function sendMessageToPWA(message) {
//     self.clients.matchAll().then((clients) => {
//         clients.forEach(client => {
//             client.postMessage(message);
//         });
//     });
// }

// // send a message every 10 seconds
// setInterval(() => {
//     sendMessageToPWA({type: 'update', data: "New data available"});
// }, 10000);

// // listen for messages from the client
// self.addEventListener('message', (event) => {
//     console.log("Service worker recieved a message: ", event.data);
    
//     // respond to the message
//     event.source.postMessage({
//         type: 'response',
//         data: "Message recieved by service worker"
//     })
// });

// create a broadcas
const channel = new BroadcastChannel('pwa_channel');

// listen for messages
channel.onmessage = (event) => {
    console.log("Received a message in Service Worker:", event.data);

    // echo the message back to the PWA
    channel.postMessage("Service Worker recieved:" + event.data);
};

// open or create the database
let db;
const dbName= "SyncDatabase";
const request = indexedDB.open(dbName, 1); // name and version needs to march the app.js

request.onerror = function (event) {
    console.error("Database error:" + event.target.error);
};

request.onsuccess = function (event) {
    // now we actually have our db
    db = event.target.result;
    console.log("Database opened successfully in Service Worker");
};

self.addEventListener("sync", function(event){
    if (event.tag === "send-data") {
        event.waitUntil(sendDatatoServer());
    }
})

// send data to server
function sendDatatoServer() {
    return getAllPendingData()
    .then(function(dataList){
        return Promise.all(
            dataList.map(function(item){
                // simulate sending the data to the server
                return new Promise((resolve, reject) => {
                    setTimeout(() => {
                        if (Math.random() > 0.1) { //90% success rate
                            resolve("Data sent successfully: ", item.data);
                            resolve(item.id);
                        } else {
                            console.log("failed to send data: ", item.data);
                            reject(new Error("Failed to send data"));
                        }
                    }, 1000);
                })
                .then(function(){
                    // if successful, remove the item from the db
                    return removeDataFormIndexedDb(item.id); 
                });
            })
        )
    })

}

function getAllPendingData(){
    return new Promise((resolve,reject) => {
        // transaction to read data from db
        const transaction = db.transaction(["pendingData"], "readonly");
        const objectStore = transaction.objectStore("pendingData");
        const request = objectStore.getAll();

        request.onsuccess = function(event) {
            resolve(event.target.result);
        };

        request.onerror = function(event) {
            reject("Error fetching data: " + event.target.error );
        };
    });
}
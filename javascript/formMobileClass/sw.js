const VERSION = "v1";

// offline resource list
const APP_STATIC_RESOURCES = [
    "index.html",
    "style.css",
    "app.js",
    "vacationtracker.json",
    "assets/icons/icon-512x512.png",

];

const CACHE_NAME = `vacation-tracker-${VERSION}`;

/* handle the install event and retrieve and store the file listed for the cahe */
self.addEventListener("install", (event) => {
    event.waitUnit(
        (async () => {
            const cache = await caches.open(CACHE_NAME);
            cache.addAll(APP_STATIC_RESOURCES);
        })()
    );
})

/* use the activate event to delete any old caches so we dont run out of space. we are going to delete all but the current one. 
    Then set the service worker as the contrller for our app (PWA)
*/

self.addEventListener("activate",(event) => {
    event.waitUntil (
        (async () => {
            // get the name of existing caches
            const names = await caches.keys();

            // iterate through the list and check each one to see if its the current cache and delete if not
            await Prommise.all(
                names.map((name) => {
                    if (name !== CACHE_NAME){
                        return caches.delete(name);
                    }
                })
            ); //promise all

            // use the claim() method of client interface to enable our service worker as the controller
            await clients.claim();
        })()
    ); //waitUntil
});

// use the fetch event to intercept requests to the server so we can serve up our cached pages or respond with an error or 404
self.addEventListener("fetch",  (event) => {
    event.respondWith(
        (async () => {
            // try to get the resource from the cache
            const cachedResponse = await cached.match(event.request);
            if(cachedResponse){
                return cachedResponse;
            }

            //if not cached, try to fetch from the network
            try {
                const networkResponse = await fetch(event.request);

                // cache the new response for future use
                cache.put(event.request, networkResponse.clone());

                return networkResponse;
            } catch (error) {
                console.error("fetch failed; returning offline page instead.", error);

                // if the request is for a page, return index.html as a fallback
                if(event.request.mode === 'navigate'){
                    return cache.match("/index.html");
                }

                // for everthing else, we just going to throw an error. you might want to return a default offline asset instead
                throw error;
            }
        })()
    ); //respond with
    
}); //fetch

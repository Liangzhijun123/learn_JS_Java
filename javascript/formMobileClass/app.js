// create constant for the form and form controls
const newVacationFormEl = document.getElementsByTagName("form")[0];
const startDateInputEl = document.getElementById("start-date");
const endDateInputEl = document.getElementById("end-date");
const pastVacationContainer = document.getElementById("past-vacations");

// listen to the form submissions
newVacationFormEl.addEventListener("submit", (event)=>{
    // prevent the form from submitting to the server
    // since we are doing everything on the client side
    event.preventDefault();
    
    // get the start and end date values
    const startDate = startDateInputEl.value;
    const endDate = endDateInputEl.value;

    // check if the dates are valid
    if (checkDatesInvalid(startDate, endDate)) {
        return; // don't submit the form, just exit
    }

    // store the new vacation in our client-side storage
    storeNewVacation(startDate, endDate);

    // refresh the UI
    renderPastVacations();

    // reset the form
    newVacationFormEl.reset();
}); // newVacationFormEl.addEventListener()

function checkDatesInvalid(startDate, endDate){
    if (!startDate || !endDate || startDate > endDate) {
        // should do error handling here
        // we're just going to clear the form if anything is invalid
        newVacationFormEl.reset();

        return true; // something is invalid
    } else {
        return false; // everything is valid
    }
} // checkDatesInvalid()

// add the storage key as an app-wide constant
const STORAGE_KEY = "vaca_tracker";

function storeNewVacation(startDate, endDate) {
    // get data from local storage
    const vacations = getAllStoredVacations(); // returns an array of strings

    // add the new vacation to the end of the array
    vacations.push({ startDate, endDate });

    // sort the vacations by start date
    vacations.sort(( a, b ) => {
        return new Date(b.startDate) - new Date(a.startDate);
    });

    // store the new array back in local storage
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(vacations));
} // storeNewVaction()

function getAllStoredVacations() {
    // get the string of vacations from local storage
    const data = window.localStorage.getItem(STORAGE_KEY);
    
    // if there is no data, return an empty array
    // otherwise, return the stored data (JSON string) as parsed JSON
    const vacations = data ? JSON.parse(data) : [];

    return vacations;
} // getAllStoredVacations()

function renderPastVacations() {
    // get the parsed string of vacations or an empty array if there aren't any
    const vacations = getAllStoredVacations();

    // exit if there aren't any vacations
    if (vacations.length === 0) {
        return;
    }

    // clear the list of pastvacationssince we're going to re-render it
    pastVacationContainer.innerHTML = "";

    const pastVacationHeader = document.createElement("h2");
    pastVacationHeader.textContent = "Past Vacations";

    const pastVacationList = document.createElement("ul");

    // loop through the vacations and create an li for each one
    vacations.forEach((vacation) => {
        const vacationEl = document.createElement("li");
        vacationEl.textContent = `From ${formatDate(vacation.startDate)}  
            to ${formatDate(vacation.endDate)}`;
        pastVacationList.appendChild(vacationEl);
    });

    pastVacationContainer.appendChild(pastVacationHeader);
    pastVacationContainer.appendChild(pastVacationList);
} // renderPastVacations()

// function to format the date
function formatDate(dateString) {
    // convert the date string to a Date object
    const date = new Date(dateString);

    // format the date into a locale specific string
    // include your locale for a better user experience
    return date.toLocaleDateString("en-US", {timeZone: "UTC"});
} // formatDate()

// start the app by rendering the past vacations on load, if there are any
renderPastVacations();

// register the service worker
if ("serviceWork" in navigator) {
    navigator.serviceWorker
        .register("sw.js")
        .then((registration) => {
            console.log("Service Worker registered with scope:", registration.scope);
        })
        .catch((error) => {
            console.error("Service Worker registration failed:", error);
        });
}

// listen for messages from the service worker
// navigator.serviceWorker.addEventListener("message", (event) => {
//     // log the message from the service worker
//     console.log("Received a message from service worker:", event.data);

//     // handle different message types
//     if (event.data.type === "update") {
//         console.log("Update received:", event.data.data);
//         // update the UI or perform other actions
//     }
// });

// // function to send a message to the service worker
// function sendMessageToSW(message) {
//     if (navigator.serviceWorker.controller) {
//         navigator.serviceWorker.controller.postMessage(message);
//     }
// }

// document.getElementById("sendButton").addEventListener("click", () => {
//     sendMessageToSW({type: "action", data: "Button clicked"});
// });

// create a broadcast channel - name here needs to match the name in the sw
const channel = new BroadcastChannel("pwa_channel");

// listen for messages
channel.onmessage = (event) => {
    console.log("Received a message in PWA:", event.data);
    document.getElementById("messages")
        .insertAdjacentHTML("beforeend", `<p>Recieved: ${event.data}</p>`);
};

// send a message when the button is clicked
document.getElementById("sendButton").addEventListener("click", () => {
    const message = "Hello from the PWA!";
    channel.postMessage(message);
    console.log("Sent message from PWA:", message);
});

// open or create the database
let db;
const dbName= "SyncDatabase";
const request = indexedDB.open(dbName, 1);

request.onerror = function (event) {
    console.error("Database error:" + event.target.error);
};

request.onsuccess = function (event) {
    // now we actually have our db
    db = event.target.result;
    console.log("Database opened successfully");
};

request.onupgradeneeded = function (event) {
    db = event.target.result;

    // create any new object stores for our db or delete any old ones from a previous version
    const objectStore = db.createObjectStore( "pendingData",
        {
            keyPath: "id",
            autoIncrement: true
        }
    )
};

// add data to our db, we need a transaction to accomplish it
function addDataToIndexedDB(data) {
    return new Promise((resolve, reject) => {
        const transaction = db.transaction(["pendingData"], "readwrite");
        const objectStore = transaction.objectStore("pendingData");
        const request = objectStore.add({data: data});

        request.onsuccess = function(event) {
            resolve();
        };

        request.onerror = function(event) {
            reject("Error storing data: " + event.target.error);
        };
    });
}

// handle form submission
document.getElementById("dataForm")
    .addEventListener("submit", function(event){
        event.preventDefault(); // don't submit the form to the server

        // get our data
        const data = document.getElementById`dataInput`.value;

        // we need to check to see if both the serviceWorker and the SyncManager are available
        if ("serveWorker" in navigator && "SyncManager" in window) {
            // we're good to add the data to the db for offline persistence
            addDataToIndexedDB(data)
            .then(() => navigator.serviceWorker.ready) // wait for the sw to be ready
            .then((registration) => {
                return registration.sync.register("send-data");
            })
            .then(() => {
                // update the UI for successful registration
                document.getElementById("status").textContent =
                    "Sync registered. Data will be sent when online.";
            })
            .catch((error) => {
                console.error("Error:", error);
            });
        } else {
            // background sync isn't supported, try to send immediately
            sendData(data)
                .then((result) => {
                    // update the UI for successful send
                    document.getElementById("status").textContent = result;
                })
                .catch((error) => {
                    // update the UI
                    document.getElementById("status").textContent = error.message;
                });
        }
    });

    // simulate sending data
    function sendData(data) {
        console.log("Attempting to send data:", data);

        return new Promise((resolve, reject)=>{
            setTimeout(() => {
                if (Math.random() > 0.5) {
                    resolve("Data sent successfully");
                } else {
                    reject(new Error("Failed to send data"));
                }
            }, 1000);
        });
    }
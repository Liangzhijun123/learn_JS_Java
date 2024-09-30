// create constants for the form and form controls
const newVacationFormEl = document.getElementById("form")[0];
const startDateInputEl = document.getElementById('start-date');
const endDateInputEl = document.getElementById('end-date');

// listen to form submission
newVacationFormEl.addEventListener("submit", (event) =>{
    //prevent the form from submitting to the server
    // since we are doing everything on the client side
    event.preventDefault();

    // get the dates from the form
    const startDate = startDateInputEl.value;
    const endDate = endDateInputEl.value; 

    // check if dates are invalid
    if (checkDatesInvalid(startDate, endDate)){
        return; //dont submit the form, just exit
    }

    // store the new vacation in our client side storage
    storeNewVacation(startDate, endDate);

    // refresh the UI
    renderPastVacations();

    //reset the form
    newVacationFormEl.reset();
});

function checkDatesInvalid(startDate, endDate){
    if (!startDate || !endDate || startDate > endDate){
        // should do error message , etc more
        // we are just going to clear the form if anything is invalid
        newVacationFormEl.reset();

        return true; //something is invalid
    } else {
        return false; //everything is good
    }
}

// add the storage key as an app-wide constant
const STORAGE_KEY = 'vaca_tracker';

function storeNewVacation(startDate, endDate){
    // get data from storage
    const vacations = getAllStoredVacations(); //returns an array of strings

    // add the new vacation at the end of the array
    vacations.push({startDate,endDate});

    // sort the array so newest to oldest vacations
    vacations.sort((a,b) => {
        return new Date(b.startDate) - new Date (a.startDate);
    });

    // store the new array back in storage
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(vacations));
} //store new vacation
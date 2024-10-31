import { useState, useEffect } from "react";
import "./people.css";
import getData from "../utils/getData";

const People = () => {
  // my vars
  const [peopleObj, setPeopleObj] = useState(null);  // Initialize as null to check if data is available
  const [loaded, setLoaded] = useState(false);

  // get the data
  useEffect(() => {
    getData("people/").then((json) => {
      console.log("people return", json);
      setPeopleObj(json);
      setLoaded(true);  // Set loaded to true once data is fetched
    });
  }, []);

  if (!loaded || !peopleObj) {
    // Show loading message until data is ready
    return (
      <>
        <h2>People Loading...</h2>
      </>
    );
  }

  return (
    <>
      <h2>{peopleObj.title}</h2>
      <h3>{peopleObj.subTitle}</h3>

      {/* output each group */}
      <h3>Faculty</h3>
      <div className="peopleList">
        {peopleObj.faculty.map((person, index) => 
          <div className="peopleListItem" key={index}>
            <h3>{person.name}</h3>
            <img src={person.imagePath} alt="this person"></img>
          </div>
        )}
      </div>

      <h3>Staff</h3>
      <div className="peopleList">
        {peopleObj.staff.map((person, index) => 
          <div className="peopleListItem" key={index}>
            <h3>{person.name}</h3>
            <img src={person.imagePath} alt="this person"></img>
          </div>
        )}
      </div>
    </>
  );
};

export default People;

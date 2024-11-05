import React, { useState, useEffect } from "react";
import { TabPane, Tab } from "semantic-ui-react";
import getData from "../utils/getData";
import PeopleGroups from "./peopleGroups";
import '../Components/people.css';

const PeopleTabs = () => {
  const [loaded, setLoaded] = useState(false);
  const [peopleObj, setPeopleObj] = useState();

  useEffect(() => {
    getData('people/')
      .then((json) => {
        console.log('people', json);
        setPeopleObj(json);
        setLoaded(true);
      })
  }, []);

  const panes = [
    {
      menuItem: "Faculty",
      render: () => (
        <TabPane>
          <PeopleGroups title="Faculty" obj={peopleObj} group="faculty" />
        </TabPane>
      ),
    },
    {
      menuItem: "Staff",
      render: () => (
        <TabPane>
          <PeopleGroups title="Staff" obj={peopleObj} group="staff" />
        </TabPane>
      ),
    },
  ];

  if (!loaded) return <h3>Loading People...</h3>;

  return (
    <>
      <h1>{peopleObj.title}</h1>
      <h3>{peopleObj.subTitle}</h3>
      <Tab panes={panes} />
    </>
  );
};

export default PeopleTabs;

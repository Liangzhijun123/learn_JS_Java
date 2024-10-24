import { useState, useEffect } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import FunctionalComp from "./components/functionalComp";
import OtherComp from "./components/otherComp";
import Title from "./components/title";

function App() {
  const [add, setAdd] = useState(0);

  const handleAdd = () => {
    if (add < 10) {
      setAdd(add + 2);
    } else {
      setAdd(add + 1);
    }
  };

  useEffect(() => {
    console.log("The 'add' state has been updated:", add);
  }, [add]);

  /*
  useEffect(() => {
    const timer = setTimeout(() => {
      setMyObj({
        title: "react is fun!",
        description: "lots and lots of words, something more meaningful than this",
      });
      setLoaded(true); // after 1 second, set loaded to true
    }, 1000);

    return () => clearTimeout(timer); // clear timeout if component unmounts
  }, []);

  if (!loaded) {
    return (
      <>
        Loading... 
      </>
    );
  }

  */

  // writing my App private vars
  // writing my private vars
  // state
  // const [getter,setter] = useState(init);
  // flag for if it is loaded yet?
  const [loaded, setLoaded] = useState(false);

  // object to hold my returned data
  const [myObj, setMyObj] = useState();

  // write my App methods
  // lets go faux the data...
  const getData = () => {
    // usually we would go out and load data!
    // today we are faking it to see useEffect and useState working
    setMyObj({
      title: "react is fun!",
      description:
        "lots and lots of words, something more meaningful than this",
    });
    setLoaded(true);
  };

  // if loaded is true
  if (!loaded)
    return (
      <>
        Loading... <button onClick={getData}>Click me</button>
      </>
    );

  return (
    <>
      <h1>{myObj.title}</h1>
      <p>{myObj.description}</p>
      <div className="inline">
        <OtherComp name="fred" age="55" />
      </div>

      <FunctionalComp name={"dan"} job="talk" />

      <p>Adding value: {add}</p>
      {add >= 10 && <p>You're at the limit</p>}
      <button onClick={handleAdd}>Click me</button>
      <Title obj = {myObj}></Title>
    </>
  );
}

export default App;

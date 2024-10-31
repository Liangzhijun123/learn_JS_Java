import { useState, useEffect } from "react";
import getData from "./utils/getData";
import "./App.css";
import About from "./Components/about";
import People from "./Components/People";
import PeopleTabs from "./Components/peopleTabs";


function App() {
  // state var
  // const [set, get] = useState(init);
  const [loadedAbout, setLoadedAbout] = useState();
  const [about, setAbout] = useState();

  useEffect(() => {
    // get the data
    getData("about/").then((json) => {
      setAbout(json);
      setLoadedAbout(true);
    });
  }, []);

  if (!loadedAbout)
    return (
      <>
        <div className="stick">
          <h1>Welcome to iSchool website</h1>
          <div>Loading...</div>
        </div>
      </>
    );

  return (
    <>
      <div className="stick">
        <h1>Welcome to iSchool website</h1>
        <div>...Menu thing...</div>
      </div>
      <div className="App">
        <div className="About">
          <About aboutData={about} />
          <hr/>
          <People/>
          <hr/>
          <PeopleTabs />
        </div>
      </div>
    </>
  );
}

export default App;

import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import FunctionalComp from './components/functionalComp';

// define my functions...
// simple inline functional component
function InlineComp({name,age}){
  return (
    <div className='mestyle'>Welcome there - {name}, you are {age}</div>
  )
}

// my application
function App() {
  

  return (
    <>
     <h1>My title</h1>
     <p>Hello there, my first react component</p>
     <InlineComp name="fred" age="55"/>
     <FunctionalComp name={"dan"} job="talk"/>
     
    </>
  )
}

export default App

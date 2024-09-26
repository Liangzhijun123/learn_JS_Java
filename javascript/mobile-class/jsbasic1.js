// variables and constants
let mutableVariable = "I can be changed";

const immutableConstant = "I cannot be changed";

// global scope
let globalVar = "I am global";

function exampleFunction() {
  // function scope varibale
  let functionVar = "I am function scoped";

  if (true) {
    // block scope
    let blockVar = "i am blocked scoped";

    var varVariable = "i am function scoped"; //no matter where i declare var, it scope to the whole function block

    console.log(globalVar); //accessiable
    console.log(functionVar); //accessiable
    console.log(blockVar); //accessiable
    console.log(varVariable); //accessiable
  }

  console.log(globalVar); //accessiable
  console.log(functionVar); //accessiable
//   console.log(blockVar); //cause an error -> because blockvar is not define
  console.log(varVariable); //accessiable
} //examplefunction

exampleFunction();

// spread operator - quickly copies all or part of another into another array
const fruit = ['apple', 'banana']
const moreFruits = [...fruit, 'grape','kiwi'];
console.log(moreFruits);

// map - creates a new array by applying a function to every element
// in the array - usually used to tranform data
const numbers = [1,2,3,4,5];
const doubledNumbers = numbers.map(num => num * 2); 
console.log(doubledNumbers);

const upperCaseFruit = fruit.map(fruit => fruit.toUpperCase());
console.log(upperCaseFruit);

const users = [
  {id: 1, name: 'alice', age: 30},
  {id: 2, name: 'bob', age: 25},
  {id: 3, name: 'charlie', age: 35},
];

const userNames = users.map((user) => user.name);
console.log(userNames);

const indexdedArray = ['a','b','c'].map((item, index) => `${index}`);
console.log(indexdedArray);

const stringNumber = ['1', '2','3','4'];
const integers = stringNumber.map((num) => parseInt(num));
console.log(integers);

// object literal notation - JSON
let person2 = {
  name: "Alice",
  age: 30,
  greet: function(){
    console.log(`Hello, I am ${this.name}`);
  },
};

// using object constructor
let car = new Object();
car.make = "Toyota";
car.model = "Corolla";

console.log(person2.name); //dot notation
console.log(person2['age']); //bracket notation

// dynamic property access
let propertyName = 'name';
console.log(person2[propertyName]);

// accessing methods
person2.greet();

//ES6 method shorthand
let calculator = {
  // method add()
  add(a,b) {
    return a + b;
  },
};

console.log(calculator.add(5,3));

// using object.create()
let animal = {
  makeSound: function (){
    console.log("some generic sound");
  },
};

let dog = Object.create(animal);
dog.makeSound();

// classes
class Animal {
  constructor (name){
    this.name = name; //add property to the class
  }

  // method function
  speak() {
    console.log(`${this.name} makes a sound`);
  }
} //animal

class Dog extends Animal { //extend inherited a class
 
  // method function
  speak() {
    console.log(`${this.name} bark`);
  }
} //animal

const rover = new Dog("rover");
rover.speak();

// object for in = for of does not work with objects
for (let attribute in person2){
  console.log("for in objects return key: " + attribute);
}

// promises - wait till things are done for 2 seconds. create smth u want to happen in background either resolve or reject like fetching data from api. fetch data and wait for api to come otherwise reject
const fetchData = () => {
  return new Promise((resolve, reject)=>{
    setTimeout(() => {
      resolve ("data fetched succesffully");
    }, 2000);
  });
};

fetchData()
  .then((data) => console.log(data)) //resolve
  .catch((error) => console.log(error)); //reject


// async /await
const getData = async () => {
  try{
    const result = await fetchData();
    console.log(result);
  } catch(error){
    console.log(error);
  }
};

getData();
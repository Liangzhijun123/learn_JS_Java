

// books object
const book1 = {
  title: "nineteen eigth four",
  author: "george orwell",
  genre: "dystopian future",
  edition: 1,
  published: 1949,
};

// html to insert
let bookTItle = `<h2> ${book1.title} </h2>`

console.log(book1);
console.log(book1.author);
document.querySelector(".container").insertAdjacentHTML("beforeend",bookTItle);

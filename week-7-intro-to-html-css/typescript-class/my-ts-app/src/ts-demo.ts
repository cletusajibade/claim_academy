// TypeScript can infer the type of a variable based on its value
let age: number = 5;

console.log(age);
let isStudent: boolean = true;

let score: number[] = [90, 97, 85];

console.log(isStudent);
console.log();

let city: string = 'St Louis';
let year = 2026;

console.log(year * 2);

function order(price: number): number {
  console.log(`Double price is: ${price * 2}`);
  console.log('Double price is:' + price * 2);

  return price * 2;
}

function getAddress(address: string): string {
  return address;
}

console.log(getAddress('1234 James Street'));

order(2.4);

let tasks: string[] = [];

function greet(name: string, greeting = 'Hello'): string {
  return `${greeting}, ${name}`;
}

console.log(greet('Cletus'));
console.log(greet('Cletus', 'Welcome to TypeScript programming'));

function logScore(score: number, label?: string): void {
  // Nullish Coalescing Operator
  // It provides a fallback/default value only
  // when the left-hand side is strictly null or undefined
  console.log(label ?? 'Score', score);
}

logScore(75, 'My Score is');

// Interfaces: describe the shape of an object
interface Project {
  id: number;
  title: string;
  description: string;
  link: string;
  featured?: boolean;
}

const weather: Project = {
  id: 1,
  title: ' Weather App',
  description: ' Forecasts for any city',
  link: 'https://github.com/weather',
  featured: true,
};

// Union types
let id: number | string;
id = 26;
id = 'A101';
id = true;

// Literal types
type Status = 'todo' | 'doing' | 'done';

let taskStatus: Status = 'todo';
console.log(taskStatus);

//let taskStatus2: Status = "canceled"; // Error

function formatId(id: number | string) {
  if (typeof id == 'number') {
    return '#' + id.toFixed(0);
  }
  return id.toUpperCase();
}

console.log(formatId(2553636));
console.log(formatId('this is converted to upper case'));

let school = 'Claim Academy';
console.log(typeof school);

//

type ToDoStatus = 'todo' | 'doing' | 'done';
type Task = {
  readonly id: number;
  title: string;
  status: ToDoStatus;
  tags?: string[];
};

const todoTasks: Task[] = [
  { id: 1, title: 'Hero Section', status: 'done' },
  { id: 2, title: 'Project Cards', status: 'doing' },
  { id: 3, title: 'Deploy', status: 'todo' },
];

const open = todoTasks.filter((t) => t.status != 'done');
console.log(open);
const titles = todoTasks.map((t) => t.title);
console.log(`Titles: ${titles}`);
const found = todoTasks.find((t) => t.id == 2);
if (found) {
  console.log(`Fopund: ${found.title}`);
}

let data: any = "Hello";
data = 53;
data.toUpperCase();
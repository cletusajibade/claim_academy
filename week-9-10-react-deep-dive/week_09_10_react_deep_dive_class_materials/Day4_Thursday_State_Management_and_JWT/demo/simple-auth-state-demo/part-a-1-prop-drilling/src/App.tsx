import { useState } from 'react';
import Header from './components/Header';
import Main from './components/Main';
import type { Task } from './types';

const INITIAL_TASKS: Task[] = [
  { id: 1, title: 'Learn useState', done: true },
  { id: 2, title: 'Learn useReducer', done: false },
  { id: 3, title: 'Learn useContext', done: false },
];

// App OWNS the tasks, so App must pass them (and every function that changes them) down
export default function App() {
  const [tasks, setTasks] = useState<Task[]>(INITIAL_TASKS);

  function addTask(title: string) {
    setTasks([...tasks, { id: Date.now(), title, done: false }]);
  }
  function toggleTask(id: number) {
    setTasks(tasks.map((t) => (t.id === id ? { ...t, done: !t.done } : t)));
  }
  function removeTask(id: number) {
    setTasks(tasks.filter((t) => t.id !== id));
  }

  return (
    <>
      <Header tasks={tasks} />
      <Main tasks={tasks} onAdd={addTask} onToggle={toggleTask} onRemove={removeTask} />
    </>
  );
}

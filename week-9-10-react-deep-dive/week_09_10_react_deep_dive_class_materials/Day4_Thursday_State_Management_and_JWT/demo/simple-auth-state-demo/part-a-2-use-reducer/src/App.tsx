import { useReducer } from 'react';
import Header from './components/Header';
import Main from './components/Main';
import { INITIAL_TASKS, tasksReducer } from './tasksReducer';

export default function App() {
  // 3. useReducer instead of useState: no more addTask / toggleTask / removeTask here
  const [tasks, dispatch] = useReducer(tasksReducer, INITIAL_TASKS);

  return (
    <>
      <Header tasks={tasks} />
      {/* One `dispatch` instead of three callback props — but still drilled down */}
      <Main tasks={tasks} dispatch={dispatch} />
    </>
  );
}

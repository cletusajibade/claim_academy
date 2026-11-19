import { useReducer, type ReactNode } from 'react';
import { INITIAL_TASKS, tasksReducer } from '../tasksReducer';
import { TaskContext } from './TaskContext';

// 3. The Provider owns the state and makes it available to EVERY component inside it
export default function TaskProvider({ children }: { children: ReactNode }) {
  const [tasks, dispatch] = useReducer(tasksReducer, INITIAL_TASKS);
  return <TaskContext.Provider value={{ tasks, dispatch }}>{children}</TaskContext.Provider>;
}

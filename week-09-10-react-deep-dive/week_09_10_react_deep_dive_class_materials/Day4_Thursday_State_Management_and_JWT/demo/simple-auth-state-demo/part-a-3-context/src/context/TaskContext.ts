import { createContext, useContext, type Dispatch } from 'react';
import type { TaskAction } from '../tasksReducer';
import type { Task } from '../types';

interface TaskContextValue {
  tasks: Task[];
  dispatch: Dispatch<TaskAction>;
}

// 1. The "channel" components can read from (undefined until a Provider fills it)
export const TaskContext = createContext<TaskContextValue | undefined>(undefined);

// 2. A small custom hook so components don't touch the context directly
export function useTasks(): TaskContextValue {
  const value = useContext(TaskContext);
  if (!value) throw new Error('useTasks must be used inside <TaskProvider>');
  return value;
}

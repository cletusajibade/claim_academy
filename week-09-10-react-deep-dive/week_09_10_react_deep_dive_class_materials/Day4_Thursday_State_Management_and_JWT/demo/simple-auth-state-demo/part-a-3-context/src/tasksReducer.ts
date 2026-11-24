import type { Task } from './types';

// 1. Every way the task list can change, as a typed "action"
export type TaskAction =
  | { type: 'added'; title: string }
  | { type: 'toggled'; id: number }
  | { type: 'removed'; id: number };

// 2. One pure function: (current state, action) → new state
export function tasksReducer(tasks: Task[], action: TaskAction): Task[] {
  switch (action.type) {
    case 'added':
      return [...tasks, { id: Date.now(), title: action.title, done: false }];
    case 'toggled':
      return tasks.map((t) => (t.id === action.id ? { ...t, done: !t.done } : t));
    case 'removed':
      return tasks.filter((t) => t.id !== action.id);
  }
}

export const INITIAL_TASKS: Task[] = [
  { id: 1, title: 'Learn useState', done: true },
  { id: 2, title: 'Learn useReducer', done: false },
  { id: 3, title: 'Learn useContext', done: false },
];

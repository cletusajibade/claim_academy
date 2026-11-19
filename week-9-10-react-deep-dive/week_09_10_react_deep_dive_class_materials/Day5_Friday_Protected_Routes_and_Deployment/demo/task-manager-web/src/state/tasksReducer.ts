import type { Task } from '../types/task';

/**
 * useReducer = all the ways task state can change, in ONE pure function.
 * Same idea as a Redux "slice" — without the library.
 */
export interface TasksState {
  items: Task[];
  status: 'idle' | 'loading' | 'success' | 'error';
  error: string | null;
}

export type TasksAction =
  | { type: 'LOAD_START' }
  | { type: 'LOAD_SUCCESS'; tasks: Task[] }
  | { type: 'LOAD_ERROR'; error: string }
  | { type: 'TASK_ADDED'; task: Task }
  | { type: 'TASK_UPDATED'; task: Task }
  | { type: 'TASK_REMOVED'; id: number }
  | { type: 'CLEAR_ERROR' };

export const initialTasksState: TasksState = { items: [], status: 'idle', error: null };

export function tasksReducer(state: TasksState, action: TasksAction): TasksState {
  switch (action.type) {
    case 'LOAD_START':
      return { ...state, status: 'loading', error: null };
    case 'LOAD_SUCCESS':
      return { items: action.tasks, status: 'success', error: null };
    case 'LOAD_ERROR':
      return { ...state, status: 'error', error: action.error };
    case 'TASK_ADDED':
      return { ...state, items: [action.task, ...state.items] };
    case 'TASK_UPDATED':
      return { ...state, items: state.items.map((t) => (t.id === action.task.id ? action.task : t)) };
    case 'TASK_REMOVED':
      return { ...state, items: state.items.filter((t) => t.id !== action.id) };
    case 'CLEAR_ERROR':
      return { ...state, error: null };
    default: {
      // Exhaustiveness check: TypeScript errors here if we forget a case
      const unreachable: never = action;
      return unreachable;
    }
  }
}

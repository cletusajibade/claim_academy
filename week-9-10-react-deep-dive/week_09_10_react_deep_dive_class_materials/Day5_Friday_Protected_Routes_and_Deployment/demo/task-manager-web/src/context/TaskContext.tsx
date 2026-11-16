import { createContext, useCallback, useContext, useEffect, useReducer, type ReactNode } from 'react';
import { taskApi } from '../api/taskApi';
import { initialTasksState, tasksReducer, type TasksState } from '../state/tasksReducer';
import type { Task, TaskRequest } from '../types/task';
import { getErrorMessage } from '../utils/errors';

interface TaskContextValue extends TasksState {
  reload: () => Promise<void>;
  addTask: (request: TaskRequest) => Promise<void>;
  toggleComplete: (task: Task) => Promise<void>;
  deleteTask: (id: number) => Promise<void>;
  clearError: () => void;
}

const TaskContext = createContext<TaskContextValue | undefined>(undefined);

/** Global task store: Context (to share) + useReducer (to update predictably). */
export function TaskProvider({ children }: { children: ReactNode }) {
  const [state, dispatch] = useReducer(tasksReducer, initialTasksState);

  const reload = useCallback(async () => {
    dispatch({ type: 'LOAD_START' });
    try {
      dispatch({ type: 'LOAD_SUCCESS', tasks: await taskApi.getAll() });
    } catch (err) {
      dispatch({ type: 'LOAD_ERROR', error: getErrorMessage(err) });
    }
  }, []);

  useEffect(() => {
    reload();
  }, [reload]);

  async function addTask(request: TaskRequest) {
    const task = await taskApi.create(request); // let the form handle validation errors
    dispatch({ type: 'TASK_ADDED', task });
  }

  async function toggleComplete(task: Task) {
    try {
      dispatch({ type: 'TASK_UPDATED', task: await taskApi.setCompleted(task.id, !task.completed) });
    } catch (err) {
      dispatch({ type: 'LOAD_ERROR', error: getErrorMessage(err) });
    }
  }

  async function deleteTask(id: number) {
    try {
      await taskApi.remove(id);
      dispatch({ type: 'TASK_REMOVED', id });
    } catch (err) {
      dispatch({ type: 'LOAD_ERROR', error: getErrorMessage(err) });
    }
  }

  const value: TaskContextValue = {
    ...state,
    reload,
    addTask,
    toggleComplete,
    deleteTask,
    clearError: () => dispatch({ type: 'CLEAR_ERROR' }),
  };

  return <TaskContext.Provider value={value}>{children}</TaskContext.Provider>;
}

export function useTaskStore(): TaskContextValue {
  const ctx = useContext(TaskContext);
  if (!ctx) throw new Error('useTaskStore must be used inside <TaskProvider>');
  return ctx;
}

import { useEffect, useState } from 'react';
import axios from 'axios';
import { taskApi } from '../api/taskApi';
import type { Task } from '../types';

// All task state + loading/error logic in one reusable custom hook (like a Java service)
export function useTasks() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  async function loadTasks() {
    setLoading(true);
    setError(null);
    try {
      setTasks(await taskApi.getAll());
    } catch (err) {
      setError(axios.isAxiosError(err) && !err.response
        ? 'Cannot reach the server. Is the API running? Is this page on port 5173?'
        : 'Could not load tasks.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadTasks();
  }, []);

  return { tasks, loading, error, reload: loadTasks };
}

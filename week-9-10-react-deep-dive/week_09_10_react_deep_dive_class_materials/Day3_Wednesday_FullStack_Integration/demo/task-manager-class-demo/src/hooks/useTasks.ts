import { useEffect, useState } from 'react';
import axios from 'axios';
import { taskApi } from '../api/taskApi';
import type { Task } from '../types';

export default function useTasks() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const sleep = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

  async function loadTask() {
    setLoading(true);
    setError(null);

    try {
      //await sleep(1000);
      setTasks(await taskApi.getAll());
    } catch (error) {
      setError(
        axios.isAxiosError(error) && !error.response
          ? 'Cannot reach the server. Is the API running? Is this page on port 5273?'
          : 'Could not load tasks',
      );
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadTask();
  }, []);

  async function addTask(title: string) {
    await sleep(3000);
    await taskApi.create(title);
    await loadTask();
  }

  return { tasks, loading, error, reload: loadTask, addTask };
}

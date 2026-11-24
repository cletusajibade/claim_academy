import { useCallback, useEffect, useState } from 'react';
import { taskApi } from '../api/taskApi';
import type { Task, TaskRequest } from '../types/task';
import { getErrorMessage } from '../utils/errors';

/**
 * All task data + API calls in one custom hook.
 * Components get: tasks, loading, error and action functions.
 */
export function useTasks() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const loadTasks = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      setTasks(await taskApi.getAll());
    } catch (err) {
      setError(getErrorMessage(err));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadTasks();
  }, [loadTasks]);

  // Step 2: create, then refresh the list from the server (simple & always correct)
  async function addTask(request: TaskRequest) {
    await taskApi.create(request); // errors bubble up to the form so it can show field errors
    await loadTasks();
  }

  // Step 3: update one item in place with the server's response
  async function toggleComplete(task: Task) {
    try {
      const updated = await taskApi.setCompleted(task.id, !task.completed);
      setTasks((prev) => prev.map((t) => (t.id === updated.id ? updated : t)));
    } catch (err) {
      setError(getErrorMessage(err));
    }
  }

  // Step 4: delete, then remove it locally
  async function deleteTask(id: number) {
    try {
      await taskApi.remove(id);
      setTasks((prev) => prev.filter((t) => t.id !== id));
    } catch (err) {
      setError(getErrorMessage(err));
    }
  }

  return { tasks, loading, error, reload: loadTasks, addTask, toggleComplete, deleteTask };
}

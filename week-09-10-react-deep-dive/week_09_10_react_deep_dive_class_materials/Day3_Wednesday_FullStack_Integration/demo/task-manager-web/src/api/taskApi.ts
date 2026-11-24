import { api } from './client';
import type { Task, TaskRequest } from '../types/task';

// Thin service layer: components never build URLs themselves.
export const taskApi = {
  async getAll(): Promise<Task[]> {
    const { data } = await api.get<Task[]>('/tasks');
    return data;
  },

  async create(request: TaskRequest): Promise<Task> {
    const { data } = await api.post<Task>('/tasks', request);
    return data;
  },

  async setCompleted(id: number, completed: boolean): Promise<Task> {
    const { data } = await api.patch<Task>(`/tasks/${id}`, { completed });
    return data;
  },

  async remove(id: number): Promise<void> {
    await api.delete(`/tasks/${id}`);
  },
};

/*
 * The same GET written with fetch — compare in class:
 *
 * const res = await fetch(`${import.meta.env.VITE_API_URL}/tasks`);
 * if (!res.ok) throw new Error(`HTTP ${res.status}`);   // fetch does NOT throw on 4xx/5xx!
 * const tasks: Task[] = await res.json();
 */

import { api } from './client';
import type { Task } from '../types';

export const taskApi = {
  async getAll(): Promise<Task[]> {
    const { data } = await api.get<Task[]>('/tasks');
    return data;
  },

  // NEW: POST /api/tasks  { "title": "..." }  → 201 + the created task
  async create(title: string): Promise<Task> {
    const { data } = await api.post<Task>('/tasks', { title });
    return data;
  },
};

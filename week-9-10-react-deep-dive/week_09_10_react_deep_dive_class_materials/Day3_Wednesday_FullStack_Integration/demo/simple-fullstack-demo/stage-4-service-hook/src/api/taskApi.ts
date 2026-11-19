import { api } from './client';
import type { Task } from '../types';

// The ONLY place that knows the task URLs (like a repository / client class in Java)
export const taskApi = {
  async getAll(): Promise<Task[]> {
    const { data } = await api.get<Task[]>('/tasks');
    return data;
  },
};

import { api } from './client';
import type { Task } from '../types';

export const taskApi = {
  async getAll(): Promise<Task[]> {
    const { data } = await api.get<Task[]>('/tasks');
    console.log(data);
    return data;
  },

  async create(title: string): Promise<Task> {
    const { data } = await api.post<Task>('/tasks', { title });
    return data;
  },
};

import { api } from './client';
import type { AuthResponse, Credentials } from '../types/auth';

export const authApi = {
  async login(credentials: Credentials): Promise<AuthResponse> {
    const { data } = await api.post<AuthResponse>('/auth/login', credentials);
    return data;
  },

  async register(credentials: Credentials): Promise<AuthResponse> {
    const { data } = await api.post<AuthResponse>('/auth/register', credentials);
    return data;
  },

  async me(): Promise<{ username: string }> {
    const { data } = await api.get<{ username: string }>('/auth/me');
    return data;
  },
};

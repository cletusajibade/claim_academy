import { useState, type ReactNode } from 'react';
import { api, setAccessToken } from '../api/client';
import type { AuthResponse } from '../types';
import { AuthContext } from './AuthContext';

// Owns "who is logged in" and shares it with the whole app (same pattern as Part A's TaskProvider)
export default function AuthProvider({ children }: { children: ReactNode }) {
  const [username, setUsername] = useState<string | null>(null);

  async function login(user: string, password: string) {
    const { data } = await api.post<AuthResponse>('/auth/login', { username: user, password });
    setAccessToken(data.token); // 1. the interceptor can now attach it
    setUsername(data.username); // 2. React re-renders → app shows the tasks page
  }

  function logout() {
    setAccessToken(null);
    setUsername(null);
  }

  return (
    <AuthContext.Provider value={{ username, isLoggedIn: username !== null, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

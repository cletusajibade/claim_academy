import { createContext, useContext, useState, type ReactNode } from 'react';
import { authApi } from '../api/authApi';
import { setAccessToken } from '../api/client';
import type { AuthResponse, Credentials } from '../types/auth';

interface AuthContextValue {
  user: { username: string; expiresAt: string } | null;
  isAuthenticated: boolean;
  login: (credentials: Credentials) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [auth, setAuth] = useState<AuthResponse | null>(null);

  async function login(credentials: Credentials) {
    const response = await authApi.login(credentials); // throws on 401 → LoginForm shows the error
    setAccessToken(response.token); // 1) axios interceptor can attach it
    setAuth(response);              // 2) React re-renders → app switches to the task page
  }

  function logout() {
    setAccessToken(null);
    setAuth(null);
  }

  const value: AuthContextValue = {
    user: auth ? { username: auth.username, expiresAt: auth.expiresAt } : null,
    isAuthenticated: auth !== null,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used inside <AuthProvider>');
  return ctx;
}

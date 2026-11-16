import { createContext, useCallback, useContext, useEffect, useState, type ReactNode } from 'react';
import { authApi } from '../api/authApi';
import { onUnauthorized, setAccessToken } from '../api/client';
import type { AuthResponse, Credentials } from '../types/auth';

interface AuthContextValue {
  user: { username: string; expiresAt: string } | null;
  isAuthenticated: boolean;
  sessionExpired: boolean;
  login: (credentials: Credentials) => Promise<void>;
  register: (credentials: Credentials) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [auth, setAuth] = useState<AuthResponse | null>(null);
  const [sessionExpired, setSessionExpired] = useState(false);

  const startSession = (response: AuthResponse) => {
    setAccessToken(response.token);
    setSessionExpired(false);
    setAuth(response);
  };

  const logout = useCallback(() => {
    setAccessToken(null);
    setAuth(null);
  }, []);

  async function login(credentials: Credentials) {
    startSession(await authApi.login(credentials));
  }

  async function register(credentials: Credentials) {
    startSession(await authApi.register(credentials)); // backend returns a token → auto-login
  }

  // 1) Server rejected our token (expired / invalid) → log out; ProtectedRoute redirects to /login
  useEffect(() => {
    return onUnauthorized(() => {
      setSessionExpired(true);
      logout();
    });
  }, [logout]);

  // 2) Log out proactively when the token's expiry time arrives
  useEffect(() => {
    if (!auth) return;
    const msLeft = new Date(auth.expiresAt).getTime() - Date.now();
    const id = setTimeout(() => {
      setSessionExpired(true);
      logout();
    }, Math.max(msLeft, 0));
    return () => clearTimeout(id);
  }, [auth, logout]);

  const value: AuthContextValue = {
    user: auth ? { username: auth.username, expiresAt: auth.expiresAt } : null,
    isAuthenticated: auth !== null,
    sessionExpired,
    login,
    register,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used inside <AuthProvider>');
  return ctx;
}

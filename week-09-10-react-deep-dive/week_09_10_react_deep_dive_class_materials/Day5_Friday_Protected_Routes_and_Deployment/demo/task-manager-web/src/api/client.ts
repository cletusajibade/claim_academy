import axios from 'axios';

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 10_000,
});

/*
 * The JWT lives in MEMORY only (this module variable + React state).
 * - Not in localStorage → injected scripts (XSS) can't simply read it from storage.
 * - Trade-off: a page refresh logs the user out. (See teaching notes: CSRF vs XSS.)
 */
let accessToken: string | null = null;

export function setAccessToken(token: string | null) {
  accessToken = token;
}

// Request interceptor: attach the token to EVERY request made with `api`
api.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

/**
 * Response interceptor: if the server says 401 on a protected call, the token is
 * missing/expired → run the callback (AuthProvider uses it to log out).
 * Returns an "eject" function so the provider can unregister it on unmount.
 */
export function onUnauthorized(callback: () => void): () => void {
  const id = api.interceptors.response.use(
    (response) => response,
    (error) => {
      const isAuthCall = String(error.config?.url ?? '').startsWith('/auth/');
      if (error.response?.status === 401 && !isAuthCall) {
        callback();
      }
      return Promise.reject(error);
    },
  );
  return () => api.interceptors.response.eject(id);
}

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

// Request interceptor: runs before EVERY request made with `api`
api.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

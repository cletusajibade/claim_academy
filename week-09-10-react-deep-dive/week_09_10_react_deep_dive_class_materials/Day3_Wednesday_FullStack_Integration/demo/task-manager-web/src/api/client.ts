import axios from 'axios';

/**
 * One configured Axios instance for the whole app.
 * The base URL comes from .env.development / .env.production at BUILD time.
 */
export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 10_000,
});

import axios from 'axios';

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

// The token is kept here (in memory) so the interceptor can read it
let accessToken: string | null = null;

export function setAccessToken(token: string | null) {
  accessToken = token;
}

// Interceptor: runs before EVERY request made with `api` and adds the header for us
api.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

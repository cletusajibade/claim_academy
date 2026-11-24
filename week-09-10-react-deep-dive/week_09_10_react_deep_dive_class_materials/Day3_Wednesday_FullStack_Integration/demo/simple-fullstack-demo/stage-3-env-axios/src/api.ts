import axios from 'axios';

// One Axios instance for the whole app. The URL lives in .env.development — nowhere else.
export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

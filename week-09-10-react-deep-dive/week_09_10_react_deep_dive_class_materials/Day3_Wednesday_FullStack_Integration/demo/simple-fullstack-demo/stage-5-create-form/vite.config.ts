import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  // strictPort: stop with an error if 5173 is busy, instead of silently moving to 5174
  // (5174 is not in the API's CORS list, so every request would be blocked)
  server: { port: 5173, strictPort: true },
});

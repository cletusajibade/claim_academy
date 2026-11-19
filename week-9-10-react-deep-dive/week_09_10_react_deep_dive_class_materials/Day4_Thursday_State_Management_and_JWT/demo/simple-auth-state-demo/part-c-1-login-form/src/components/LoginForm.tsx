import { useState, type FormEvent } from 'react';
import axios from 'axios';
import { api } from '../api/client';
import type { AuthResponse } from '../types';

export default function LoginForm({ onLogin }: { onLogin: (token: string) => void }) {
  const [username, setUsername] = useState('demo');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    setError('');
    try {
      // 1. Send the username + password to Spring Boot
      const { data } = await api.post<AuthResponse>('/auth/login', { username, password });
      // 2. Spring sent back a token → give it to App
      onLogin(data.token);
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.status === 401) {
        setError('Wrong username or password.');
      } else {
        setError('Cannot reach the server.');
      }
    }
  }

  return (
    <main>
      <h1>Log in</h1>
      <form className="stack" onSubmit={handleSubmit}>
        <input placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input placeholder="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <button type="submit">Log in</button>
        {error && <p className="error">{error}</p>}
      </form>
      <p>Demo account: demo / password123</p>
    </main>
  );
}

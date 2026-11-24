import { useState, type FormEvent } from 'react';
import { useAuth } from '../context/AuthContext';
import { getErrorMessage } from '../utils/errors';

export default function LoginForm() {
  const { login } = useAuth();
  const [username, setUsername] = useState('demo');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (!username.trim() || !password) {
      setError('Username and password are required.');
      return;
    }
    setSubmitting(true);
    setError(null);
    try {
      await login({ username: username.trim(), password });
      // No navigation needed today: App re-renders because isAuthenticated changed.
    } catch (err) {
      setError(getErrorMessage(err));
      setSubmitting(false);
    }
  }

  return (
    <form className="card stack" onSubmit={handleSubmit} style={{ maxWidth: 380, margin: '40px auto' }} noValidate>
      <h1>Log in</h1>
      {error && <div className="banner">{error}</div>}
      <div className="field">
        <label htmlFor="username">Username</label>
        <input id="username" autoComplete="username" value={username} onChange={(e) => setUsername(e.target.value)} />
      </div>
      <div className="field">
        <label htmlFor="password">Password</label>
        <input id="password" type="password" autoComplete="current-password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </div>
      <button type="submit" disabled={submitting}>{submitting ? 'Signing in…' : 'Sign in'}</button>
      <p className="muted">Demo account: <code>demo</code> / <code>password123</code></p>
    </form>
  );
}

import { useState, type FormEvent } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useFakeAuth } from '../context/FakeAuthContext';

export default function LoginPage() {
  const { login } = useFakeAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const from = (location.state as { from?: string } | null)?.from ?? '/dashboard';
  const [name, setName] = useState('');

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (!name.trim()) return;
    login(name.trim());
    navigate(from, { replace: true }); // send them back where they were going
  }

  return (
    <div className="card">
      <h1>Log in (fake)</h1>
      <p className="muted">You were redirected from <code>{from}</code>. Real JWT login arrives on Day 4.</p>
      <form className="row" onSubmit={handleSubmit}>
        <input placeholder="Your name" value={name} onChange={(e) => setName(e.target.value)} />
        <button type="submit">Log in</button>
      </form>
    </div>
  );
}

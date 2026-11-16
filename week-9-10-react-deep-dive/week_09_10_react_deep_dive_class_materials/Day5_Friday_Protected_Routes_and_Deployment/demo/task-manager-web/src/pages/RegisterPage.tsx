import { useState, type FormEvent } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { getErrorMessage, getFieldErrors } from '../utils/errors';

export default function RegisterPage() {
  const { register } = useAuth();
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirm, setConfirm] = useState('');
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [formError, setFormError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  function validate(): Record<string, string> {
    const e: Record<string, string> = {};
    if (!/^[a-zA-Z0-9_.]{3,30}$/.test(username)) e.username = '3–30 characters: letters, digits, _ or .';
    if (password.length < 8) e.password = 'At least 8 characters.';
    if (confirm !== password) e.confirm = 'Passwords do not match.';
    return e;
  }

  async function handleSubmit(ev: FormEvent) {
    ev.preventDefault();
    const clientErrors = validate();
    setErrors(clientErrors);
    setFormError(null);
    if (Object.keys(clientErrors).length > 0) return;

    setSubmitting(true);
    try {
      await register({ username, password });
      navigate('/tasks', { replace: true });
    } catch (err) {
      setErrors(getFieldErrors(err));
      setFormError(getErrorMessage(err)); // e.g. 409 "Username 'x' is already taken"
      setSubmitting(false);
    }
  }

  return (
    <div className="container">
      <form className="card stack auth-card" onSubmit={handleSubmit} noValidate>
        <h1>Create account</h1>
        {formError && <div className="banner">{formError}</div>}
        <div className="field">
          <label htmlFor="username">Username</label>
          <input id="username" autoComplete="username" value={username} onChange={(e) => setUsername(e.target.value)} />
          {errors.username && <span className="error">{errors.username}</span>}
        </div>
        <div className="field">
          <label htmlFor="password">Password</label>
          <input id="password" type="password" autoComplete="new-password" value={password} onChange={(e) => setPassword(e.target.value)} />
          {errors.password && <span className="error">{errors.password}</span>}
        </div>
        <div className="field">
          <label htmlFor="confirm">Confirm password</label>
          <input id="confirm" type="password" autoComplete="new-password" value={confirm} onChange={(e) => setConfirm(e.target.value)} />
          {errors.confirm && <span className="error">{errors.confirm}</span>}
        </div>
        <button type="submit" disabled={submitting}>{submitting ? 'Creating…' : 'Register'}</button>
        <p className="muted">Already have an account? <Link to="/login">Log in</Link></p>
      </form>
    </div>
  );
}

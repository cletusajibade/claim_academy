import { Link, useLocation } from 'react-router-dom';

export default function NotFoundPage() {
  const { pathname } = useLocation();
  return (
    <div className="card">
      <h1>404 — Page not found</h1>
      <p>No route matches <code>{pathname}</code>.</p>
      <Link to="/">Go home</Link>
    </div>
  );
}

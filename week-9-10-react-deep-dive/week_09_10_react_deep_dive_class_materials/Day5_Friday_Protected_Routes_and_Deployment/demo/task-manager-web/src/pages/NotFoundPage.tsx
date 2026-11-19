import { Link } from 'react-router-dom';

export default function NotFoundPage() {
  return (
    <div className="container">
      <div className="card">
        <h1>404 — Page not found</h1>
        <Link to="/tasks">Back to my tasks</Link>
      </div>
    </div>
  );
}

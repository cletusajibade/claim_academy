import { Link } from 'react-router-dom';

export default function HomePage() {
  return (
    <div className="card">
      <h1>Welcome to Course Hub</h1>
      <p>A multi-page single-page application: the browser never reloads, React Router swaps components based on the URL.</p>
      <div className="row">
        <Link to="/courses">Browse courses</Link>
        <Link to="/courses?q=react">Search “react” (query string)</Link>
        <Link to="/courses/999">A missing course</Link>
        <Link to="/this/does/not/exist">A missing page (404)</Link>
      </div>
    </div>
  );
}

import { Link } from "react-router";

export default function NotFoundPage() {
  return (
    <div>
      <h1>404 - Page not found</h1>
      <Link to="/">Go home</Link>
    </div>
  );
}

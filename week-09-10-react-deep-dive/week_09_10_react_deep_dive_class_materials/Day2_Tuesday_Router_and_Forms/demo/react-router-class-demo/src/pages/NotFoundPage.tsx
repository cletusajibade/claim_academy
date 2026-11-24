import { Link } from 'react-router'

function NotFoundPage() {
  return (
      <div>
          <h2>404 - Page not found</h2>
          <Link to="/">Go home</Link>
    </div>
  )
}

export default NotFoundPage
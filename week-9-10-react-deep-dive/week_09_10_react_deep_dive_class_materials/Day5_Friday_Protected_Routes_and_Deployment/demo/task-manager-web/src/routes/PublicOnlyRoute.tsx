import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

/** /login and /register: if you're already logged in, go to your tasks instead. */
export default function PublicOnlyRoute() {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? <Navigate to="/tasks" replace /> : <Outlet />;
}

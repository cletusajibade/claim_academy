import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

/**
 * Layout route that guards every child route.
 *   <Route element={<ProtectedRoute />}>
 *     <Route path="/tasks" element={<TasksPage />} />
 *   </Route>
 * Not logged in → redirect to /login and remember where the user wanted to go.
 *
 * NOTE: this is a UX guard only. Real security is the 401 from Spring Security.
 */
export default function ProtectedRoute() {
  const { isAuthenticated } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }
  return <Outlet />;
}

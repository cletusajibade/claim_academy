import type { ReactNode } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useFakeAuth } from '../context/FakeAuthContext';

/** Redirects to /login and remembers where the user was trying to go. */
export default function RequireAuth({ children }: { children: ReactNode }) {
  const { user } = useFakeAuth();
  const location = useLocation();
  if (!user) {
    return <Navigate to="/login" replace state={{ from: location.pathname }} />;
  }
  return <>{children}</>;
}

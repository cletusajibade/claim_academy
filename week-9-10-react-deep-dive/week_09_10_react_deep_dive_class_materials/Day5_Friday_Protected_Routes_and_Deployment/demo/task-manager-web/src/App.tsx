import { Navigate, Route, Routes } from 'react-router-dom';
import AppLayout from './components/AppLayout';
import AccountPage from './pages/AccountPage';
import LoginPage from './pages/LoginPage';
import NotFoundPage from './pages/NotFoundPage';
import RegisterPage from './pages/RegisterPage';
import TasksPage from './pages/TasksPage';
import ProtectedRoute from './routes/ProtectedRoute';
import PublicOnlyRoute from './routes/PublicOnlyRoute';

export default function App() {
  return (
    <Routes>
      {/* Public pages (redirect to /tasks if already logged in) */}
      <Route element={<PublicOnlyRoute />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Route>

      {/* Everything that shows stored data is protected */}
      <Route element={<ProtectedRoute />}>
        <Route element={<AppLayout />}>
          <Route path="/tasks" element={<TasksPage />} />
          <Route path="/account" element={<AccountPage />} />
        </Route>
      </Route>

      <Route path="/" element={<Navigate to="/tasks" replace />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
}

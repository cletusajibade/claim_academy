import { NavLink, Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { TaskProvider } from '../context/TaskContext';
import TaskStats from './TaskStats';

/** Shell for all logged-in pages: nav bar + the task store + <Outlet/>. */
export default function AppLayout() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    logout();                                  // 1) clear the token from state
    navigate('/login', { replace: true });     // 2) send the user to /login
  }

  return (
    <TaskProvider>
      <nav className="topnav">
        <div className="container">
          <NavLink to="/tasks">Tasks</NavLink>
          <NavLink to="/account">Account</NavLink>
          <span className="spacer" />
          <TaskStats />
          <span style={{ color: '#fff' }}>{user?.username}</span>
          <button className="secondary" onClick={handleLogout}>Log out</button>
        </div>
      </nav>
      <main className="container">
        <Outlet />
      </main>
    </TaskProvider>
  );
}

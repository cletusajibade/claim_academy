import LoginForm from './components/LoginForm';
import TaskStats from './components/TaskStats';
import { useAuth } from './context/AuthContext';
import { TaskProvider } from './context/TaskContext';
import TasksPage from './pages/TasksPage';

export default function App() {
  const { isAuthenticated, user, logout } = useAuth();

  // Day 4: simple conditional rendering. Day 5 replaces this with protected routes.
  if (!isAuthenticated) {
    return <div className="container"><LoginForm /></div>;
  }

  return (
    // TaskProvider mounts only after login, so the first GET /tasks already carries the token
    <TaskProvider>
      <div className="container">
        <div className="row" style={{ justifyContent: 'space-between' }}>
          <h1>Task Manager</h1>
          <div className="row">
            <TaskStats />
            <span>Signed in as <b>{user?.username}</b></span>
            <button className="secondary" onClick={logout}>Log out</button>
          </div>
        </div>
        <TasksPage />
      </div>
    </TaskProvider>
  );
}

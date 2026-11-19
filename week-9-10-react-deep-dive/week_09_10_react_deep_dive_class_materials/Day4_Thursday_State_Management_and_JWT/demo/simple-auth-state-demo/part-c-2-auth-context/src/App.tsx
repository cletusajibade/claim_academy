import Header from './components/Header';
import LoginForm from './components/LoginForm';
import TasksPage from './components/TasksPage';
import { useAuth } from './context/AuthContext';

// No token props anywhere: components ask useAuth()
export default function App() {
  const { isLoggedIn } = useAuth();

  if (!isLoggedIn) return <LoginForm />;
  return (
    <>
      <Header />
      <TasksPage />
    </>
  );
}

import { useState } from 'react';
import LoginForm from './components/LoginForm';
import TasksPage from './components/TasksPage';

export default function App() {
  // The JWT lives in React state = in MEMORY (refresh the page → it's gone)
  const [token, setToken] = useState<string | null>(null);

  if (!token) {
    return <LoginForm onLogin={setToken} />;
  }
  return <TasksPage token={token} />;
}

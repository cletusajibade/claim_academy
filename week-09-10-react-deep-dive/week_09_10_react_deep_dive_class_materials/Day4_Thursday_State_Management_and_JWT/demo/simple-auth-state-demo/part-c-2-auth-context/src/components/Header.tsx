import { useAuth } from '../context/AuthContext';

export default function Header() {
  const { username, logout } = useAuth();
  return (
    <header>
      <strong>Task Manager</strong>
      <span>
        Signed in as <b>{username}</b> <button onClick={logout}>Log out</button>
      </span>
    </header>
  );
}

import { useEffect, useState } from 'react';
import axios from 'axios';
import { api } from './api';

interface Task {
  id: number;
  title: string;
  completed: boolean;
}

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  async function loadTasks() {
    setLoading(true);
    setError(null);
    try {
      const { data } = await api.get<Task[]>('/tasks'); // JSON already parsed; throws on 4xx/5xx
      setTasks(data);
    } catch (err) {
      if (axios.isAxiosError(err) && !err.response) {
        // No response at all: API stopped, wrong URL, or blocked by CORS
        setError('Cannot reach the server. Is the API running? Is this page on port 5173?');
      } else {
        setError(err instanceof Error ? err.message : 'Something went wrong');
      }
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadTasks();
  }, []);

  if (loading) return <main>Loading tasks…</main>;
  if (error) {
    return (
      <main>
        <p className="error">{error}</p>
        <button onClick={loadTasks}>Try again</button>
      </main>
    );
  }

  return (
    <main>
      <h1>Tasks ({tasks.length})</h1>
      <p>API: {import.meta.env.VITE_API_URL}</p>
      {tasks.map((t) => (
        <div className="card" key={t.id}>
          <span className={t.completed ? 'done' : ''}>{t.title}</span>
        </div>
      ))}
    </main>
  );
}

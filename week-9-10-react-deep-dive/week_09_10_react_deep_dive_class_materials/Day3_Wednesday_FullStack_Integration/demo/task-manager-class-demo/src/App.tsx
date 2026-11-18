import { useEffect, useState } from 'react';
import { api } from './api';
import axios from 'axios';

interface Task {
  id: number;
  title: string;
  completed: boolean;
}

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const sleep = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

  async function loadTask() {
    setLoading(true);
    setError(null);

    try {
      await sleep(2000);

      const { data } = await api.get<Task[]>('/tasks');
      setTasks(data);
    } catch (error) {
      if (axios.isAxiosError(error) && !error.response) {
        setError(
          'Cannot rech the server. Is the API running? Is the page on port 5173',
        );
      } else {
        setError(
          error instanceof Error ? error.message : 'Something went wrong',
        );
      }
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadTask();
  }, []);

  if (loading) {
    return <main style={{ textAlign: 'center' }}> Loading tasks...</main>;
  }

  if (error) {
    return (
      <main>
        <p className="error">Could not load tasks: {error}</p>
        <button onClick={loadTask}>Try again</button>
      </main>
    );
  }

  return (
    <div>
      <main>
        <h1>Tasks</h1>
        {tasks.map((t) => (
          <div className="card" key={t.id}>
            <span className={t.completed ? 'done' : ''}>{t.title}</span>
          </div>
        ))}
      </main>
    </div>
  );
}

export default App;

import { useEffect, useState } from 'react';

interface Task {
  id: number;
  title: string;
  completed: boolean;
}

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);            // NEW
  const [error, setError] = useState<string | null>(null); // NEW

  async function loadTasks() {
    setLoading(true);
    setError(null);
    try {
      const res = await fetch('http://localhost:8080/api/tasks');
      if (!res.ok) throw new Error(`Server answered ${res.status}`); // fetch does NOT throw on 404/500
      setTasks(await res.json());
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Something went wrong');
    } finally {
      setLoading(false); // runs on success AND on failure
    }
  }

  useEffect(() => {
    loadTasks();
  }, []);

  // Every request has three states: loading, error, data
  if (loading) return <main>Loading tasks…</main>;
  if (error) {
    return (
      <main>
        <p className="error">Could not load tasks: {error}</p>
        <button onClick={loadTasks}>Try again</button>
      </main>
    );
  }

  return (
    <main>
      <h1>Tasks ({tasks.length})</h1>
      {tasks.map((t) => (
        <div className="card" key={t.id}>
          <span className={t.completed ? 'done' : ''}>{t.title}</span>
        </div>
      ))}
    </main>
  );
}

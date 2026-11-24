import { useEffect, useState } from 'react';
import { api } from '../api/client';
import type { Task } from '../types';

export default function TasksPage({ token }: { token: string }) {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadTasks() {
      try {
        // 3. Prove who we are: send the token in the Authorization header
        const { data } = await api.get<Task[]>('/tasks', {
          headers: { Authorization: `Bearer ${token}` },
        });
        setTasks(data);
      } catch {
        setError('Could not load tasks (is the token valid?).');
      }
    }
    loadTasks();
  }, [token]);

  return (
    <main>
      <h1>My tasks</h1>
      <p>Your token (paste it into jwt.io):</p>
      <p><code>{token}</code></p>
      {error && <p className="error">{error}</p>}
      {tasks.map((t) => (
        <div className="card" key={t.id}>
          <span className={t.completed ? 'done' : ''}>{t.title}</span>
        </div>
      ))}
    </main>
  );
}

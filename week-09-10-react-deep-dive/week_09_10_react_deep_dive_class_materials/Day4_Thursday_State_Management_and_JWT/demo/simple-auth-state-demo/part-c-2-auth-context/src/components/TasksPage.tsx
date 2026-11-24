import { useEffect, useState } from 'react';
import { api } from '../api/client';
import type { Task } from '../types';

export default function TasksPage() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadTasks() {
      try {
        const { data } = await api.get<Task[]>('/tasks'); // no header here: the interceptor adds it
        setTasks(data);
      } catch {
        setError('Could not load tasks.');
      }
    }
    loadTasks();
  }, []);

  return (
    <main>
      <h1>My tasks</h1>
      {error && <p className="error">{error}</p>}
      {tasks.map((t) => (
        <div className="card" key={t.id}>
          <span className={t.completed ? 'done' : ''}>{t.title}</span>
        </div>
      ))}
    </main>
  );
}

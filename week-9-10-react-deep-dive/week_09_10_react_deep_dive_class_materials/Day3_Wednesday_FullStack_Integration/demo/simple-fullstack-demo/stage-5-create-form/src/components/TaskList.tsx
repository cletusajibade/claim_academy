import type { Task } from '../types';

export default function TaskList({ tasks }: { tasks: Task[] }) {
  if (tasks.length === 0) return <p>No tasks yet.</p>;
  return (
    <>
      {tasks.map((t) => (
        <div className="card" key={t.id}>
          <span className={t.completed ? 'done' : ''}>{t.title}</span>
        </div>
      ))}
    </>
  );
}

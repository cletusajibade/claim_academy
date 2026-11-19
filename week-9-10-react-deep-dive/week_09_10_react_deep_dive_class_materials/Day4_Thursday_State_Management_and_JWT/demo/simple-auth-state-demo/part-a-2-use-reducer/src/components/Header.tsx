import type { Task } from '../types';

export default function Header({ tasks }: { tasks: Task[] }) {
  const done = tasks.filter((t) => t.done).length;
  return (
    <header>
      <strong>My Tasks</strong>
      <span className="badge">{done} / {tasks.length} done</span>
    </header>
  );
}

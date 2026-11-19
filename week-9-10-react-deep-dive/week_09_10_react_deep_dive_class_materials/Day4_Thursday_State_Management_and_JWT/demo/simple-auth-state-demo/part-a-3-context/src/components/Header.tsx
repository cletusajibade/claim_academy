import { useTasks } from '../context/TaskContext';

export default function Header() {
  const { tasks } = useTasks(); // read directly — no props
  const done = tasks.filter((t) => t.done).length;
  return (
    <header>
      <strong>My Tasks</strong>
      <span className="badge">{done} / {tasks.length} done</span>
    </header>
  );
}

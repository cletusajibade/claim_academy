import { useTaskStore } from '../context/TaskContext';

/** Lives in the header — far away from TasksPage — yet reads the same store. No prop drilling. */
export default function TaskStats() {
  const { items, status } = useTaskStore();
  if (status !== 'success') return null;
  const done = items.filter((t) => t.completed).length;
  return <span className="badge">{done}/{items.length} done</span>;
}

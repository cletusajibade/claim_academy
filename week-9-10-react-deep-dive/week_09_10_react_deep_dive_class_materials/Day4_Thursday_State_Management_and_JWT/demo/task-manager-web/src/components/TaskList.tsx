import type { Task } from '../types/task';
import TaskCard from './TaskCard';

interface Props {
  tasks: Task[];
  onToggle: (task: Task) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

export default function TaskList({ tasks, onToggle, onDelete }: Props) {
  if (tasks.length === 0) {
    return <p className="muted">No tasks yet — add one above.</p>;
  }
  return (
    <>
      {tasks.map((t) => (
        <TaskCard key={t.id} task={t} onToggle={onToggle} onDelete={onDelete} />
      ))}
    </>
  );
}

import type { Task } from '../types';

interface TaskItemProps {
  task: Task;
  onToggle: (id: number) => void;
  onRemove: (id: number) => void;
}

// Finally, the component that actually USES onToggle and onRemove
export default function TaskItem({ task, onToggle, onRemove }: TaskItemProps) {
  return (
    <div className="card">
      <label className={task.done ? 'done' : ''}>
        <input type="checkbox" checked={task.done} onChange={() => onToggle(task.id)} /> {task.title}
      </label>
      <button className="link" onClick={() => onRemove(task.id)}>remove</button>
    </div>
  );
}

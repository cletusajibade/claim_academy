import type { Task } from '../types';
import TaskItem from './TaskItem';

interface TaskListProps {
  tasks: Task[];
  onToggle: (id: number) => void;
  onRemove: (id: number) => void;
}

// ⚠️ TaskList passes onToggle and onRemove down again without using them
export default function TaskList({ tasks, onToggle, onRemove }: TaskListProps) {
  return (
    <>
      {tasks.map((t) => (
        <TaskItem key={t.id} task={t} onToggle={onToggle} onRemove={onRemove} />
      ))}
    </>
  );
}

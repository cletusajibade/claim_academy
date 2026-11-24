import type { Task } from '../types';
import AddTaskForm from './AddTaskForm';
import TaskList from './TaskList';

interface MainProps {
  tasks: Task[];
  onAdd: (title: string) => void;
  onToggle: (id: number) => void;
  onRemove: (id: number) => void;
}

// ⚠️ Main doesn't USE any of these props. It only passes them along = prop drilling
export default function Main({ tasks, onAdd, onToggle, onRemove }: MainProps) {
  return (
    <main>
      <AddTaskForm onAdd={onAdd} />
      <TaskList tasks={tasks} onToggle={onToggle} onRemove={onRemove} />
    </main>
  );
}

import { useTasks } from '../context/TaskContext';
import type { Task } from '../types';

// `task` is still a prop (it's specific to this item); dispatch comes from context
export default function TaskItem({ task }: { task: Task }) {
  const { dispatch } = useTasks();
  return (
    <div className="card">
      <label className={task.done ? 'done' : ''}>
        <input type="checkbox" checked={task.done} onChange={() => dispatch({ type: 'toggled', id: task.id })} />{' '}
        {task.title}
      </label>
      <button className="link" onClick={() => dispatch({ type: 'removed', id: task.id })}>remove</button>
    </div>
  );
}

import TaskForm from '../components/TaskForm';
import TaskList from '../components/TaskList';
import { useTaskStore } from '../context/TaskContext';

export default function TasksPage() {
  const { items, status, error, reload, addTask, toggleComplete, deleteTask } = useTaskStore();
  const remaining = items.filter((t) => !t.completed).length;

  return (
    <>
      <TaskForm onCreate={addTask} />
      <div className="row" style={{ justifyContent: 'space-between' }}>
        <h2>My tasks {status === 'success' && <span className="badge">{remaining} open</span>}</h2>
        <button className="secondary" onClick={reload} disabled={status === 'loading'}>Refresh</button>
      </div>
      {status === 'loading' && <p><span className="spinner" /> Loading tasks…</p>}
      {error && (
        <div className="banner">
          {error} <button className="secondary" onClick={reload}>Try again</button>
        </div>
      )}
      {status === 'success' && <TaskList tasks={items} onToggle={toggleComplete} onDelete={deleteTask} />}
    </>
  );
}

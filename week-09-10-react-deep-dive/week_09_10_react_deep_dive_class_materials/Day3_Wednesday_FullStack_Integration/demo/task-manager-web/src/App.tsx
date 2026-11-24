import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import { useTasks } from './hooks/useTasks';

export default function App() {
  const { tasks, loading, error, reload, addTask, toggleComplete, deleteTask } = useTasks();
  const remaining = tasks.filter((t) => !t.completed).length;

  return (
    <div className="container">
      <h1>Task Manager</h1>
      <p className="muted">API: <code>{import.meta.env.VITE_API_URL}</code></p>

      <TaskForm onCreate={addTask} />

      <div className="row" style={{ justifyContent: 'space-between' }}>
        <h2>Tasks {!loading && <span className="badge">{remaining} open</span>}</h2>
        <button className="secondary" onClick={reload} disabled={loading}>Refresh</button>
      </div>

      {/* The three UI states every API call needs: loading, error, data */}
      {loading && <p><span className="spinner" /> Loading tasks…</p>}
      {error && (
        <div className="banner">
          {error} <button className="secondary" onClick={reload}>Try again</button>
        </div>
      )}
      {!loading && !error && <TaskList tasks={tasks} onToggle={toggleComplete} onDelete={deleteTask} />}
    </div>
  );
}

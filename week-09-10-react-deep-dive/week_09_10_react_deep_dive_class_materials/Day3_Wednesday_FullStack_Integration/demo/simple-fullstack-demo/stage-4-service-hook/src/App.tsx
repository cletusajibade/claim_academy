import TaskList from './components/TaskList';
import { useTasks } from './hooks/useTasks';

// The component only decides WHAT to show — no URLs, no fetching logic
export default function App() {
  const { tasks, loading, error, reload } = useTasks();

  return (
    <main>
      <h1>Tasks</h1>
      {loading && <p>Loading tasks…</p>}
      {error && (
        <p className="error">
          {error} <button onClick={reload}>Try again</button>
        </p>
      )}
      {!loading && !error && <TaskList tasks={tasks} />}
    </main>
  );
}

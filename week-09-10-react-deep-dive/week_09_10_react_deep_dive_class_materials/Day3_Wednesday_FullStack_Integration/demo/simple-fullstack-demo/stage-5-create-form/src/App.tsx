import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import { useTasks } from './hooks/useTasks';

export default function App() {
  const { tasks, loading, error, reload, addTask } = useTasks();

  return (
    <main>
      <h1>Tasks</h1>
      <TaskForm onAdd={addTask} />
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

import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import useTasks from './hooks/useTasks';

function App() {
  const { tasks, loading, error, reload, addTask } = useTasks();
  return (
    <div>
      <main>
        <h1>Tasks</h1>
        <TaskForm onAdd={addTask} />
        {loading && <p>Loading tasks...</p>}
        {error && (
          <p className="error">
            {error} <button onClick={reload}>Try again</button>
          </p>
        )}
        {!loading && !error && <TaskList tasks={tasks} />}
      </main>
    </div>
  );
}

export default App;

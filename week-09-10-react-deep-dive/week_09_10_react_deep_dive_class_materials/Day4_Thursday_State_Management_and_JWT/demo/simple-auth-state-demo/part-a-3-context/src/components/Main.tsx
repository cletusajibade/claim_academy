import AddTaskForm from './AddTaskForm';
import TaskList from './TaskList';

// ✅ Main no longer passes anything along
export default function Main() {
  return (
    <main>
      <AddTaskForm />
      <TaskList />
    </main>
  );
}

import { useTasks } from '../context/TaskContext';
import TaskItem from './TaskItem';

export default function TaskList() {
  const { tasks } = useTasks();
  return (
    <>
      {tasks.map((t) => (
        <TaskItem key={t.id} task={t} />
      ))}
    </>
  );
}

import type { Dispatch } from 'react';
import type { TaskAction } from '../tasksReducer';
import type { Task } from '../types';
import AddTaskForm from './AddTaskForm';
import TaskList from './TaskList';

interface MainProps {
  tasks: Task[];
  dispatch: Dispatch<TaskAction>;
}

// ⚠️ Still prop drilling: Main only passes tasks and dispatch along
export default function Main({ tasks, dispatch }: MainProps) {
  return (
    <main>
      <AddTaskForm dispatch={dispatch} />
      <TaskList tasks={tasks} dispatch={dispatch} />
    </main>
  );
}

import type { Dispatch } from 'react';
import type { TaskAction } from '../tasksReducer';
import type { Task } from '../types';
import TaskItem from './TaskItem';

interface TaskListProps {
  tasks: Task[];
  dispatch: Dispatch<TaskAction>;
}

export default function TaskList({ tasks, dispatch }: TaskListProps) {
  return (
    <>
      {tasks.map((t) => (
        <TaskItem key={t.id} task={t} dispatch={dispatch} />
      ))}
    </>
  );
}

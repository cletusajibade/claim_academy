import type { Dispatch } from 'react';
import type { TaskAction } from '../tasksReducer';
import type { Task } from '../types';

interface TaskItemProps {
  task: Task;
  dispatch: Dispatch<TaskAction>;
}

export default function TaskItem({ task, dispatch }: TaskItemProps) {
  return (
    <div className="card">
      <label className={task.done ? 'done' : ''}>
        <input
          type="checkbox"
          checked={task.done}
          onChange={() => dispatch({ type: 'toggled', id: task.id })} // describe WHAT happened
        />{' '}
        {task.title}
      </label>
      <button className="link" onClick={() => dispatch({ type: 'removed', id: task.id })}>remove</button>
    </div>
  );
}

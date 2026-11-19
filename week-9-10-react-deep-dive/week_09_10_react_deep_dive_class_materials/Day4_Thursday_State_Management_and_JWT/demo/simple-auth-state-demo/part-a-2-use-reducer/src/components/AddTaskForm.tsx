import { useState, type Dispatch, type FormEvent } from 'react';
import type { TaskAction } from '../tasksReducer';

export default function AddTaskForm({ dispatch }: { dispatch: Dispatch<TaskAction> }) {
  const [title, setTitle] = useState(''); // form-only state stays local

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (!title.trim()) return;
    dispatch({ type: 'added', title: title.trim() });
    setTitle('');
  }

  return (
    <form onSubmit={handleSubmit}>
      <input placeholder="New task" value={title} onChange={(e) => setTitle(e.target.value)} />
      <button type="submit">Add</button>
    </form>
  );
}

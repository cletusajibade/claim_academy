import { useState, type FormEvent } from 'react';
import { useTasks } from '../context/TaskContext';

export default function AddTaskForm() {
  const { dispatch } = useTasks();
  const [title, setTitle] = useState('');

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

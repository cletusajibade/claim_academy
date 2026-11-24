import { useState, type FormEvent } from 'react';
import axios from 'axios';
import type { ApiError } from '../types';

export default function TaskForm({
  onAdd,
}: {
  onAdd: (title: string) => Promise<void>;
}) {
  const [title, setTitle] = useState('');
  const [error, setError] = useState('');
  const [saving, setSaving] = useState(false);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    setSaving(true);
    setError('');

    try {
      await onAdd(title);
      setTitle(''); // clear the box after a successful save
    } catch (err) {
      // Spring's @Valid sent 400 { fieldErrors: { title: "Title is required" } }
      if (axios.isAxiosError<ApiError>(err) && err.response?.status === 400) {
        setError(
          err.response.data.fieldErrors.title ?? err.response.data.message,
        );
      } else {
        setError('Could not save the task.');
      }
    } finally {
      setSaving(false);
    }
  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="New Task"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <button
          type="submit"
          disabled={saving}
          className={saving ? 'disabled' : ''}
        >
          {saving ? 'Saving...' : 'Add'}
        </button>
      </form>
      {error && <p className="error">{error}</p>}
    </div>
  );
}

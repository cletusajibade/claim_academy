import { useState, type FormEvent } from 'react';
import type { TaskRequest } from '../types/task';
import { getErrorMessage, getFieldErrors } from '../utils/errors';

interface Props {
  onCreate: (request: TaskRequest) => Promise<void>;
}

export default function TaskForm({ onCreate }: Props) {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const [fieldErrors, setFieldErrors] = useState<Record<string, string>>({});
  const [formError, setFormError] = useState<string | null>(null);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    // Client-side validation for fast feedback…
    if (!title.trim()) {
      setFieldErrors({ title: 'Title is required' });
      return;
    }
    setSubmitting(true);
    setFieldErrors({});
    setFormError(null);
    try {
      await onCreate({ title: title.trim(), description: description.trim() || undefined, dueDate: dueDate || null });
      setTitle('');
      setDescription('');
      setDueDate('');
    } catch (err) {
      // …but the server is the source of truth: show its validation messages too
      setFieldErrors(getFieldErrors(err));
      setFormError(getErrorMessage(err));
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <form className="card" onSubmit={handleSubmit} noValidate>
      <h2>New task</h2>
      {formError && <div className="banner">{formError}</div>}
      <div className="form-grid">
        <div className="field">
          <label htmlFor="title">Title *</label>
          <input id="title" value={title} onChange={(e) => setTitle(e.target.value)} maxLength={100} />
          {fieldErrors.title && <span className="error">{fieldErrors.title}</span>}
        </div>
        <div className="field">
          <label htmlFor="dueDate">Due date</label>
          <input id="dueDate" type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} />
        </div>
        <div className="field full">
          <label htmlFor="description">Description</label>
          <textarea id="description" rows={2} value={description} onChange={(e) => setDescription(e.target.value)} maxLength={500} />
          {fieldErrors.description && <span className="error">{fieldErrors.description}</span>}
        </div>
      </div>
      <div style={{ marginTop: 12 }}>
        <button type="submit" disabled={submitting}>{submitting ? 'Saving…' : 'Add task'}</button>
      </div>
    </form>
  );
}

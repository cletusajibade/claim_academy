import { useState } from 'react';
import type { Task } from '../types/task';

interface Props {
  task: Task;
  onToggle: (task: Task) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

export default function TaskCard({ task, onToggle, onDelete }: Props) {
  const [busy, setBusy] = useState(false);
  const overdue = !task.completed && task.dueDate !== null && task.dueDate < new Date().toISOString().slice(0, 10);

  async function run(action: () => Promise<void>) {
    setBusy(true);
    try {
      await action();
    } finally {
      setBusy(false);
    }
  }

  function handleDelete() {
    // Step 4: confirm before deleting
    if (window.confirm(`Delete "${task.title}"? This cannot be undone.`)) {
      run(() => onDelete(task.id));
    }
  }

  return (
    <div className={`card task ${task.completed ? 'done' : ''}`}>
      <div>
        <h3>{task.title}</h3>
        {task.description && <p style={{ margin: '4px 0' }}>{task.description}</p>}
        <span className={overdue ? 'overdue' : 'muted'}>
          {task.dueDate ? `Due ${task.dueDate}${overdue ? ' (overdue)' : ''}` : 'No due date'}
        </span>
      </div>
      <div className="row">
        <button className="secondary" disabled={busy} onClick={() => run(() => onToggle(task))}>
          {task.completed ? 'Undo' : 'Mark complete'}
        </button>
        <button className="danger" disabled={busy} onClick={handleDelete}>Delete</button>
      </div>
    </div>
  );
}

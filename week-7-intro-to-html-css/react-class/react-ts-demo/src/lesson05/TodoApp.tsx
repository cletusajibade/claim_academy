import { useState } from 'react';
import type { ChangeEvent, SubmitEvent } from 'react';

interface Todo {
  id: number;
  text: string;
  done: boolean;
}

type Filter = 'all' | 'active' | 'done';

const FILTERS: Filter[] = ['all', 'active', 'done'];

export default function TodoApp() {
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: 'Learn React', done: true },
    { id: 2, text: 'Learn typescript', done: false },
  ]);

  const [text, setText] = useState<string>('');

  const [filter, setFilter] = useState<Filter>('all');

  function handleChange(e: ChangeEvent<HTMLInputElement>) {
    setText(e.target.value);
  }

  function handleSubmit(e: SubmitEvent<HTMLFormElement>) {
    e.preventDefault(); // prevent the page from reloading
    const trimmed = text.trim();
    if (trimmed === '') {
      return;
    }

    const newTodo: Todo = { id: Date.now(), text: trimmed, done: false };
    setTodos([...todos, newTodo]);
    setText('');
  }

  function toggleTodo(id: number) {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, done: !todo.done } : todo,
      ),
    );
  }

  function deleteTodo(id: number) {
    setTodos(todos.filter((todo) => todo.id !== id));
  }

  const visibleTodos = todos.filter((todo) => {
    if (filter === 'active') return !todo.done;
    if (filter === 'done') return todo.done;

    return true;
  });

  const remaining = todos.filter((todo) => !todo.done).length;

  return (
    <div className="stack" style={{ padding: '15px' }}>
      <form onSubmit={handleSubmit} className="row">
        <input
          value={text}
          onChange={handleChange}
          placeholder="What do you need to do?"
          aria-label="New to-do"
        />
        <button type="submit" disabled={text.trim() === ''}>
          Add
        </button>
      </form>

      <div className="row">
        {FILTERS.map((f) => (
          <button
            key={f}
            className={f === filter ? '' : 'secondary'}
            onClick={() => setFilter(f)}
          >
            {f}
          </button>
        ))}
      </div>

      {visibleTodos.length === 0 ? (
        <p className="muted">Nothing to show here.</p>
      ) : (
        <ul className="todo-list">
          {visibleTodos.map((todo) => (
            <li key={todo.id}>
              <label className={todo.done ? 'done' : ''}>
                <input
                  type="checkbox"
                  checked={todo.done}
                  onChange={() => toggleTodo(todo.id)}
                />
                {todo.text}
              </label>
              <button
                className="link"
                onClick={() => deleteTodo(todo.id)}
                aria-label={`Delete ${todo.text}`}
              >
                ✕
              </button>
            </li>
          ))}
        </ul>
      )}

      <p className="muted">
        {remaining} of {todos.length} left to do
      </p>
    </div>
  );
}

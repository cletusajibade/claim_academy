import { useState, type FormEvent } from 'react';

interface Profile {
  name: string;
  email: string;
  newsletter: boolean;
}

interface Todo {
  id: number;
  text: string;
  done: boolean;
}

export default function UseStateDemo() {
  // 1) Primitive state + stale-state trap
  const [count, setCount] = useState(0);

  function addThreeBroken() {
    // All three read the SAME `count` captured by this render → only +1
    setCount(count + 1);
    setCount(count + 1);
    setCount(count + 1);
  }

  function addThreeCorrect() {
    // Functional updates receive the latest queued value → +3
    setCount((c) => c + 1);
    setCount((c) => c + 1);
    setCount((c) => c + 1);
  }

  // 2) Object state — always create a NEW object (spread), never mutate
  const [profile, setProfile] = useState<Profile>({ name: '', email: '', newsletter: false });

  // 3) Array state — map/filter/spread return NEW arrays
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: 'Review the rendering model', done: true },
    { id: 2, text: 'Practice functional updates', done: false },
  ]);
  const [text, setText] = useState('');

  // Lazy initial state: the function runs only on the first render
  const [startedAt] = useState(() => new Date().toLocaleTimeString());

  function addTodo(e: FormEvent) {
    e.preventDefault();
    if (!text.trim()) return;
    setTodos((prev) => [...prev, { id: Date.now(), text: text.trim(), done: false }]);
    setText('');
  }

  const toggle = (id: number) =>
    setTodos((prev) => prev.map((t) => (t.id === id ? { ...t, done: !t.done } : t)));
  const remove = (id: number) => setTodos((prev) => prev.filter((t) => t.id !== id));

  return (
    <div>
      <div className="card">
        <h2>Batching &amp; functional updates</h2>
        <p>count = <strong>{count}</strong></p>
        <div className="row">
          <button className="secondary" onClick={addThreeBroken}>+3 (broken)</button>
          <button onClick={addThreeCorrect}>+3 (functional update)</button>
          <button className="secondary" onClick={() => setCount(0)}>reset</button>
        </div>
        <p className="muted">React 18 batches all updates inside one event handler into a single re-render.</p>
      </div>

      <div className="card">
        <h2>Object state</h2>
        <div className="row">
          <input placeholder="Name" value={profile.name}
            onChange={(e) => setProfile({ ...profile, name: e.target.value })} />
          <input placeholder="Email" value={profile.email}
            onChange={(e) => setProfile({ ...profile, email: e.target.value })} />
          <label>
            <input type="checkbox" checked={profile.newsletter}
              onChange={(e) => setProfile((p) => ({ ...p, newsletter: e.target.checked }))} /> Newsletter
          </label>
        </div>
        <pre>{JSON.stringify(profile, null, 2)}</pre>
      </div>

      <div className="card">
        <h2>Array state</h2>
        <form className="row" onSubmit={addTodo}>
          <input value={text} onChange={(e) => setText(e.target.value)} placeholder="New todo" />
          <button type="submit">Add</button>
        </form>
        <ul>
          {todos.map((t) => (
            <li key={t.id}>
              <label style={{ textDecoration: t.done ? 'line-through' : 'none' }}>
                <input type="checkbox" checked={t.done} onChange={() => toggle(t.id)} /> {t.text}
              </label>{' '}
              <button className="secondary" onClick={() => remove(t.id)}>x</button>
            </li>
          ))}
        </ul>
        <p className="muted">Lazy initial state computed once at {startedAt}.</p>
      </div>
    </div>
  );
}

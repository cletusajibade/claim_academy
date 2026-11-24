import { useEffect, useState } from 'react';
import { fetchUsers, type User } from '../api/fakeApi';

export default function UseEffectDemo() {
  return (
    <div>
      <DocumentTitleEffect />
      <TimerEffect />
      <DataFetchingEffect />
    </div>
  );
}

/** 1) Synchronize with something outside React: the browser tab title. */
function DocumentTitleEffect() {
  const [clicks, setClicks] = useState(0);
  useEffect(() => {
    document.title = `Clicked ${clicks} times`;
  }, [clicks]); // re-run only when `clicks` changes
  return (
    <div className="card">
      <h2>1. Dependency array</h2>
      <button onClick={() => setClicks((c) => c + 1)}>Clicked {clicks} times (check the tab title)</button>
      <ul className="muted">
        <li><code>useEffect(fn)</code> — after every render</li>
        <li><code>useEffect(fn, [])</code> — once after mount</li>
        <li><code>useEffect(fn, [a, b])</code> — after mount and whenever a or b change</li>
      </ul>
    </div>
  );
}

/** 2) Cleanup: stop the interval when the component unmounts or `running` changes. */
function TimerEffect() {
  const [running, setRunning] = useState(false);
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    if (!running) return;
    console.log('[TimerEffect] starting interval');
    const id = setInterval(() => setSeconds((s) => s + 1), 1000);
    return () => {
      console.log('[TimerEffect] cleanup: clearing interval');
      clearInterval(id);
    };
  }, [running]);

  return (
    <div className="card">
      <h2>2. Cleanup function</h2>
      <p><strong>{seconds}s</strong></p>
      <div className="row">
        <button onClick={() => setRunning((r) => !r)}>{running ? 'Pause' : 'Start'}</button>
        <button className="secondary" onClick={() => setSeconds(0)}>Reset</button>
      </div>
      <p className="muted">Without cleanup, StrictMode would start two intervals and the timer would count double.</p>
    </div>
  );
}

/** 3) Fetching data with loading / error states and AbortController. */
function DataFetchingEffect() {
  const [query, setQuery] = useState('');
  const [fail, setFail] = useState(false);
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const controller = new AbortController();
    setLoading(true);
    setError(null);

    fetchUsers(query, { fail, signal: controller.signal })
      .then(setUsers)
      .catch((err: Error) => {
        if (err.name !== 'AbortError') setError(err.message);
      })
      .finally(() => {
        if (!controller.signal.aborted) setLoading(false);
      });

    // If query changes before the request finishes, cancel the old one (avoids race conditions)
    return () => controller.abort();
  }, [query, fail]);

  return (
    <div className="card">
      <h2>3. Fetching data</h2>
      <div className="row">
        <input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search users…" />
        <label><input type="checkbox" checked={fail} onChange={(e) => setFail(e.target.checked)} /> Simulate server error</label>
      </div>
      {loading && <p className="muted">Loading…</p>}
      {error && <p className="error">Error: {error}</p>}
      {!loading && !error && (
        <ul>
          {users.map((u) => <li key={u.id}>{u.name} — {u.email}</li>)}
          {users.length === 0 && <li className="muted">No users match.</li>}
        </ul>
      )}
    </div>
  );
}

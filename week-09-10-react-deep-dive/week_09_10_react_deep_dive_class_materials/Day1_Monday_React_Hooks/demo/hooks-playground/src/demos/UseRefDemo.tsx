import { useEffect, useRef, useState } from 'react';

export default function UseRefDemo() {
  // 1) DOM reference
  const inputRef = useRef<HTMLInputElement>(null);

  // 2) Mutable value that survives renders but does NOT trigger re-render
  const intervalId = useRef<number | null>(null);
  const [elapsed, setElapsed] = useState(0);

  // 3) Remember the previous value of some state
  const [name, setName] = useState('');
  const prevName = useRef('');
  useEffect(() => {
    prevName.current = name; // runs after render, so during render we still see the old value
  }, [name]);

  function start() {
    if (intervalId.current !== null) return; // already running
    intervalId.current = window.setInterval(() => setElapsed((e) => e + 100), 100);
  }
  function stop() {
    if (intervalId.current !== null) {
      clearInterval(intervalId.current);
      intervalId.current = null;
    }
  }
  useEffect(() => stop, []); // clear on unmount

  return (
    <div>
      <div className="card">
        <h2>1. Referencing a DOM element</h2>
        <div className="row">
          <input ref={inputRef} placeholder="Click the button to focus me" />
          <button onClick={() => inputRef.current?.focus()}>Focus input</button>
        </div>
      </div>
      <div className="card">
        <h2>2. Storing a mutable value (interval id)</h2>
        <p><strong>{(elapsed / 1000).toFixed(1)}s</strong></p>
        <div className="row">
          <button onClick={start}>Start</button>
          <button className="secondary" onClick={stop}>Stop</button>
          <button className="secondary" onClick={() => setElapsed(0)}>Reset</button>
        </div>
      </div>
      <div className="card">
        <h2>3. Previous value</h2>
        <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Type a name" />
        <p>Current: <b>{name || '—'}</b> · Previous render: <b>{prevName.current || '—'}</b></p>
      </div>
      <p className="muted">Rule of thumb: if changing it should update the screen → useState. If not → useRef.</p>
    </div>
  );
}

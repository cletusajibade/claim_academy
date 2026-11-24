import { useState } from 'react';
import { useDebounce } from '../hooks/useDebounce';
import { useToggle } from '../hooks/useToggle';

export default function CustomHooksDemo() {
  const [text, setText] = useState('');
  const debounced = useDebounce(text, 500);
  const [showHelp, toggleHelp] = useToggle(false);

  return (
    <div className="card">
      <h2>Custom hooks = reusable stateful logic</h2>
      <input value={text} onChange={(e) => setText(e.target.value)} placeholder="Type quickly…" />
      <p>Live value: <b>{text}</b></p>
      <p>Debounced (500 ms): <b>{debounced}</b> <span className="muted">← use this to trigger API searches</span></p>
      <button className="secondary" onClick={toggleHelp}>{showHelp ? 'Hide' : 'Show'} rules of hooks</button>
      {showHelp && (
        <ul>
          <li>Only call hooks at the top level — never inside loops, conditions, or nested functions.</li>
          <li>Only call hooks from React function components or other custom hooks.</li>
          <li>A custom hook's name must start with <code>use</code>.</li>
        </ul>
      )}
    </div>
  );
}

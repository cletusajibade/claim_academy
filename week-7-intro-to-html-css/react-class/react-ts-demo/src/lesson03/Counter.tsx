import { useState } from 'react';

export default function Counter() {
  // Type is infered implicitly
  const [count, setCount] = useState(0);

  // Explicit typing
  const [step, setStep] = useState<number>(1);

  const isEven = count % 2 === 0;

  return (
    <div className="stack">
      <p className="big-number">
        You clicked {count} {count === 1 ? 'time' : 'times'}
      </p>
      <p>
        {count} is an <strong>{isEven ? 'even' : 'odd'}</strong> number
      </p>
      <div className="row">
        <button onClick={() => setCount((prev) => prev - step)}>-{step}</button>
        <button onClick={() => setCount((prev) => prev + step)}>+{step}</button>
        <button className="secondary" onClick={() => setCount(0)}>
          -Reset
        </button>
      </div>
      <label>
        Step size:{' '}
        <select value={step} onChange={(e) => setStep(Number(e.target.value))}>
          <option value={1}>1</option>
          <option value={5}>5</option>
          <option value={10}>10</option>
        </select>
      </label>
    </div>
  );
}

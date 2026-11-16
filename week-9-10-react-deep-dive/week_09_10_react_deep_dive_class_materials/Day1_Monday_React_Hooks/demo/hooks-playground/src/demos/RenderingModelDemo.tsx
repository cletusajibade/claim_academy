import { useRef, useState } from 'react';

/**
 * "Why can't I just use a normal variable?"
 * - A normal variable is re-created (reset) on every render, and changing it
 *   does NOT tell React to re-render.
 * - State survives between renders AND changing it schedules a re-render.
 */
export default function RenderingModelDemo() {
  let normalCount = 0; // reset to 0 every time this function runs
  const [stateCount, setStateCount] = useState(0);
  const renders = useRef(0);
  renders.current += 1; // counting renders without causing more renders

  console.log('[RenderingModelDemo] render #', renders.current, { normalCount, stateCount });

  function incrementNormal() {
    normalCount += 1;
    console.log('normalCount is now', normalCount, '— but the screen does not change!');
  }

  return (
    <div>
      <div className="card">
        <h2>Normal variable vs. state</h2>
        <div className="row">
          <div className="stack">
            <strong>let normalCount = {normalCount}</strong>
            <button className="secondary" onClick={incrementNormal}>normalCount++</button>
          </div>
          <div className="stack">
            <strong>stateCount = {stateCount}</strong>
            <button onClick={() => setStateCount(stateCount + 1)}>setStateCount(+1)</button>
          </div>
        </div>
        <p className="muted">This component has rendered <span className="badge">{renders.current}</span> times
          (StrictMode doubles renders in development).</p>
      </div>
      <div className="card">
        <h3>The React rendering model in 4 steps</h3>
        <ol>
          <li><b>Trigger</b> — initial mount, or a <code>setState</code> call.</li>
          <li><b>Render</b> — React calls your component function to get new JSX.</li>
          <li><b>Commit</b> — React diffs the result and updates only the changed DOM nodes.</li>
          <li><b>Effects</b> — after paint, React runs your <code>useEffect</code> callbacks.</li>
        </ol>
      </div>
    </div>
  );
}

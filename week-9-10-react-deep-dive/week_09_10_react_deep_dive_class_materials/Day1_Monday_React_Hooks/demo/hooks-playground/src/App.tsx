import { useState } from 'react';
import RenderingModelDemo from './demos/RenderingModelDemo';
import UseStateDemo from './demos/UseStateDemo';
import UseEffectDemo from './demos/UseEffectDemo';
import UseRefDemo from './demos/UseRefDemo';
import UseContextDemo from './demos/UseContextDemo';
import UseMemoDemo from './demos/UseMemoDemo';
import CustomHooksDemo from './demos/CustomHooksDemo';

// A simple tab switcher built with useState (no router yet — that's Day 2).
const DEMOS = {
  'Rendering model': RenderingModelDemo,
  useState: UseStateDemo,
  useEffect: UseEffectDemo,
  useRef: UseRefDemo,
  useContext: UseContextDemo,
  'useMemo / useCallback': UseMemoDemo,
  'Custom hooks': CustomHooksDemo,
} as const;

type DemoName = keyof typeof DEMOS;

export default function App() {
  const [active, setActive] = useState<DemoName>('Rendering model');
  const ActiveDemo = DEMOS[active];

  return (
    <div className="container">
      <h1>React 18 Hooks Playground</h1>
      <p className="muted">Week 9 · Day 1 — open the browser console to see render logs.</p>
      <div className="tabs">
        {(Object.keys(DEMOS) as DemoName[]).map((name) => (
          <button key={name} className={name === active ? 'active' : ''} onClick={() => setActive(name)}>
            {name}
          </button>
        ))}
      </div>
      <ActiveDemo />
    </div>
  );
}

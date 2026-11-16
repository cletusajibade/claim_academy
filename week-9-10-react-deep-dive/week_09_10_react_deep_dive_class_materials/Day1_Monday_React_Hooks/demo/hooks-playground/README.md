# Day 1 Demo — React 18 Hooks Playground (React + TypeScript + Vite)

Each tab is a self-contained demo used during the lecture.

| Tab | File | What it shows |
|---|---|---|
| Rendering model | `src/demos/RenderingModelDemo.tsx` | Normal variable vs. state, render counting |
| useState | `src/demos/UseStateDemo.tsx` | Batching, functional updates, object/array immutability, lazy init |
| useEffect | `src/demos/UseEffectDemo.tsx` | Dependency arrays, cleanup, data fetching w/ AbortController |
| useRef | `src/demos/UseRefDemo.tsx` | DOM refs, mutable values, previous value |
| useContext | `src/demos/UseContextDemo.tsx`, `src/context/ThemeContext.tsx` | Provider + custom `useTheme()` hook |
| useMemo / useCallback | `src/demos/UseMemoDemo.tsx` | Expensive filter, `memo` child |
| Custom hooks | `src/hooks/*.ts` | `useDebounce`, `useToggle` |

## Run
```bash
npm install
npm run dev      # http://localhost:5173
```
Open the browser console — several demos log renders and effect cleanups.

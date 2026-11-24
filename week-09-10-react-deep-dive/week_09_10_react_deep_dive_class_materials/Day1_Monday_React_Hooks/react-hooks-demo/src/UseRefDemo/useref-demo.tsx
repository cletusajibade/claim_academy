import { useRef } from 'react';
export default function UseRefDemo() {
  const inputRef = useRef(null);
  function focusInput() {
    inputRef.current?.focus();
  }
  return (
    <div>
      <h2>Input Focus Example</h2>
      <input type="text" ref={inputRef} placeholder="Enter your name" />
      <button onClick={focusInput}>Focus Input</button>
    </div>
  );
}

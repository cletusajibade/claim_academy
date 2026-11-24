import { useState, useEffect } from 'react';
export default function UseEffectDemo() {
  const [count, setCount] = useState<number>(0);

  useEffect(() => {
      document.title = `Clicked ${count} time`;
  }, [count]);

  return (
    <div>
      {/* <h2>You Clicked {count} times</h2> */}
      <button onClick={() => setCount(count + 1)}>Click Me</button>
      <p id="para" style={{ fontSize: 20 }}>
        This is a paragraph
      </p>
    </div>
  );
}

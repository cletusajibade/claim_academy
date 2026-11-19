import { useState, useRef } from 'react';

// Create a react application that takes a user name
// and welcomes them to Claim Academy

function App() {
  const [message, setMessage] = useState('');
  const nameRef = useRef(null);

  function handleClick() {
    const newName = nameRef.current?.value;
    setMessage(`Hi ${newName}, welcome to Claim Academy`);
  }

  return (
    <div>
      <h2>Java Coding Club</h2>
      <input type="text" ref={nameRef} placeholder="Enter your name" />
      <div>
        {' '}
        <button onClick={handleClick}>Click Me</button>
        <p>{message}</p>
      </div>
    </div>
  );
}

export default App;

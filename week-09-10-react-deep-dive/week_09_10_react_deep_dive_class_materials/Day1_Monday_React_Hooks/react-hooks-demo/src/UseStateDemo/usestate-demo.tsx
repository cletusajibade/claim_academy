import { useState } from 'react';

function UseStateDemo() {
  // pure javascript/typescript

  const number1 = 4; // local variable
  const [number, setNumber] = useState(10); //state variable
  const [name, setName] = useState('');

  return (
    <div>
      <p> Number state changes</p>
      <button onClick={() => setNumber(number + 1)}>{number}</button>
          <div>{name}</div>
          <button onClick={()=>setName("Starlet")}>Change Name</button>
    </div>
  );
}

export default UseStateDemo;

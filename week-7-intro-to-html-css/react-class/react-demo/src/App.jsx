import { useState } from 'react';
import ProductList from './product-list';

function App() {
  const [count, setCount] = useState(6);
  return (
    <div>

      <p>You clicked {count} time</p>
      <button onClick={() => setCount(count + 1)}>Click Me</button>
      <div>
        <ProductList />
      </div>
    </div>
  );
}

export default App;

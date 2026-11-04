import Greeting from './greeting';
import Bio from './bio';
import Welcome from './welcome';
import { useState } from 'react';
import ProductList from './product-list';

function App() {
  const [count, setCount] = useState(6);
  return (
    <div>
      <Greeting message="Welcome to React" number="5" />
      <Bio />
      <Welcome />
      <p>You clicked {count} time</p>
      <button onClick={() => setCount(count + 1)}>Click Me</button>
      <div>
        <ProductList />
      </div>
    </div>
  );
}

export default App;

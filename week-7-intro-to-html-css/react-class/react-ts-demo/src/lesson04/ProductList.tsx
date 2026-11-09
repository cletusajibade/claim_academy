import { useState } from 'react';
import { products } from '../data/products';
import ProductCard from './ProductCard';

export default function ProductList() {
  const [cartCount, setCartCount] = useState<number>(0);
  const [lastAdded, setLastAdded] = useState<string | null>(null);

  function handleAdd(productName: string) {
    setCartCount((prev) => prev + 1);
    setLastAdded(productName);
  }

  return (
    <section>
      <h3>
        Cart: {cartCount} {cartCount === 1 ? 'item' : 'items'}
      </h3>
      {lastAdded !== null && <p className="muted">Last Added: {lastAdded}</p>}
      <div className="product-grid">
        {products.map((p) => (
          <ProductCard key={p.id} product={p} onAdd={() => handleAdd(p.name)} />
        ))}
      </div>
    </section>
  );
}

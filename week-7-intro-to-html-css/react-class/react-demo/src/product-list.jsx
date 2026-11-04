import { useState } from 'react';
import ProductCard from './product-card';

export default function ProductList() {
  const [cartCount, setCartCount] = useState(0);

  const products = [
    { id: 1, name: 'Bag', price: 23.5, image: 'src/images/bag.jpg' },
    { id: 2, name: 'Shoe', price: 44.5, image: 'src/images/shoe.jpg' },
    { id: 3, name: 'Wristwatch', price: 127.99, image: 'src/images/wristwatch.png' },
  ];

  return (
    <section>
      <h2> Cart: {cartCount} items</h2>
      <div className="product-grid">
        {products.map((p) => (
          <ProductCard
            key={p.id}
            name={p.name}
            price={p.price}
            image={p.image}
            onAdd={() => setCartCount(cartCount + 1)}
          />
        ))}
      </div>
    </section>
  );
}

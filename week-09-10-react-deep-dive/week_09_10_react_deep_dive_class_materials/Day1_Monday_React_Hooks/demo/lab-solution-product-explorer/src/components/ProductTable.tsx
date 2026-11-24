import type { Product } from '../data/products';

export default function ProductTable({ products }: { products: Product[] }) {
  if (products.length === 0) return <p className="muted">No products match your search.</p>;
  return (
    <table>
      <thead>
        <tr><th>Name</th><th>Category</th><th>Price</th></tr>
      </thead>
      <tbody>
        {products.map((p) => (
          <tr key={p.id}>
            <td>{p.name}</td>
            <td>{p.category}</td>
            <td>${p.price.toFixed(2)}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

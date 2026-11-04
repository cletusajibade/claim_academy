import './css/style.css'
export default function ProductCard({ name, price, image, onAdd }) {
  return (
    <article className="card">
      <img src={image} alt={name} />
      <h3>{name}</h3>
      <p>${price.toFixed(2)}</p>
      <button onClick={onAdd}>Add to Cart</button>
    </article>
  );
}

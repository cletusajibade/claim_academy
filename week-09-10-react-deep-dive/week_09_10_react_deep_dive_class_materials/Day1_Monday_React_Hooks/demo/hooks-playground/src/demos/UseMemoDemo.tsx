import { memo, useCallback, useMemo, useState } from 'react';

interface Product {
  id: number;
  name: string;
  price: number;
}

// 20,000 fake products
const PRODUCTS: Product[] = Array.from({ length: 20000 }, (_, i) => ({
  id: i + 1,
  name: `Product ${i + 1}`,
  price: Math.round(((i * 37) % 500) + 5),
}));

function slowFilter(items: Product[], term: string, maxPrice: number): Product[] {
  console.time('slowFilter');
  const start = performance.now();
  while (performance.now() - start < 120) { /* simulate expensive work */ }
  const result = items.filter((p) => p.name.toLowerCase().includes(term.toLowerCase()) && p.price <= maxPrice);
  console.timeEnd('slowFilter');
  return result;
}

export default function UseMemoDemo() {
  const [term, setTerm] = useState('');
  const [maxPrice, setMaxPrice] = useState(500);
  const [dark, setDark] = useState(false); // unrelated state
  const [useMemoOn, setUseMemoOn] = useState(true);

  // With useMemo: recompute only when term or maxPrice change
  const memoized = useMemo(() => slowFilter(PRODUCTS, term, maxPrice), [term, maxPrice]);
  const visible = useMemoOn ? memoized : slowFilter(PRODUCTS, term, maxPrice);

  // useCallback keeps the same function identity so memo(Child) can skip re-rendering
  const handleSelect = useCallback((p: Product) => alert(`Selected ${p.name}`), []);

  return (
    <div className="card" style={dark ? { background: '#1d2433', color: '#fff' } : undefined}>
      <h2>useMemo: skip expensive recalculations</h2>
      <div className="row">
        <input value={term} onChange={(e) => setTerm(e.target.value)} placeholder="Filter by name" />
        <label>Max price ${maxPrice}
          <input type="range" min={5} max={500} value={maxPrice} onChange={(e) => setMaxPrice(Number(e.target.value))} />
        </label>
        <button className="secondary" onClick={() => setDark((d) => !d)}>Toggle theme (unrelated state)</button>
        <label><input type="checkbox" checked={useMemoOn} onChange={(e) => setUseMemoOn(e.target.checked)} /> useMemo on</label>
      </div>
      <p className="muted">Turn useMemo off, then toggle the theme: the UI lags because the filter re-runs on every render.</p>
      <p>{visible.length.toLocaleString()} matching products</p>
      <ProductList items={visible.slice(0, 10)} onSelect={handleSelect} />
    </div>
  );
}

const ProductList = memo(function ProductList({ items, onSelect }: { items: Product[]; onSelect: (p: Product) => void }) {
  console.log('[ProductList] render');
  return (
    <ul>
      {items.map((p) => (
        <li key={p.id}>
          {p.name} — ${p.price} <button className="secondary" onClick={() => onSelect(p)}>select</button>
        </li>
      ))}
    </ul>
  );
});

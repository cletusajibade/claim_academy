import { useMemo, useState } from 'react';
import { PRODUCTS } from './data/products';
import ProductSearch from './components/ProductSearch';
import ProductTable from './components/ProductTable';
import Pagination from './components/Pagination';

export type SortKey = 'name-asc' | 'name-desc' | 'price-asc' | 'price-desc';
export const PAGE_SIZE = 5;

export default function App() {
  const [term, setTerm] = useState('');
  const [sort, setSort] = useState<SortKey>('name-asc');
  const [page, setPage] = useState(1);

  // Partner A: filter (pure derived data — NOT stored in state)
  const filtered = useMemo(
    () => PRODUCTS.filter((p) => p.name.toLowerCase().includes(term.trim().toLowerCase())),
    [term],
  );

  // Together: sort (copy first — never sort the original array in place)
  const sorted = useMemo(() => {
    const copy = [...filtered];
    switch (sort) {
      case 'name-asc': return copy.sort((a, b) => a.name.localeCompare(b.name));
      case 'name-desc': return copy.sort((a, b) => b.name.localeCompare(a.name));
      case 'price-asc': return copy.sort((a, b) => a.price - b.price);
      case 'price-desc': return copy.sort((a, b) => b.price - a.price);
    }
  }, [filtered, sort]);

  // Partner B: pagination
  const totalPages = Math.max(1, Math.ceil(sorted.length / PAGE_SIZE));
  const pageItems = sorted.slice((page - 1) * PAGE_SIZE, page * PAGE_SIZE);

  // When the search or sort changes, jump back to page 1.
  // Do it in the event handler (not in a useEffect) — one render instead of two.
  const handleTermChange = (value: string) => { setTerm(value); setPage(1); };
  const handleSortChange = (value: SortKey) => { setSort(value); setPage(1); };

  return (
    <div className="container">
      <h1>Product Explorer</h1>
      <div className="card">
        <div className="row">
          <ProductSearch term={term} onTermChange={handleTermChange} />
          <label>Sort by{' '}
            <select value={sort} onChange={(e) => handleSortChange(e.target.value as SortKey)}>
              <option value="name-asc">Name (A–Z)</option>
              <option value="name-desc">Name (Z–A)</option>
              <option value="price-asc">Price (low → high)</option>
              <option value="price-desc">Price (high → low)</option>
            </select>
          </label>
        </div>
        <p className="muted">{sorted.length} result(s)</p>
        <ProductTable products={pageItems} />
        <Pagination page={page} totalPages={totalPages} onPageChange={setPage} />
      </div>
    </div>
  );
}

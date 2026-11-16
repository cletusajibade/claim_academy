interface Props {
  term: string;
  onTermChange: (value: string) => void;
}

export default function ProductSearch({ term, onTermChange }: Props) {
  return (
    <input
      type="search"
      placeholder="Search products…"
      value={term}
      onChange={(e) => onTermChange(e.target.value)}
      aria-label="Search products"
    />
  );
}

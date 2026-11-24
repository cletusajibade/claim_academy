interface Props {
  page: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

export default function Pagination({ page, totalPages, onPageChange }: Props) {
  return (
    <div className="row" style={{ marginTop: 12 }}>
      <button className="secondary" disabled={page <= 1} onClick={() => onPageChange(page - 1)}>← Previous</button>
      <span>Page {page} of {totalPages}</span>
      <button className="secondary" disabled={page >= totalPages} onClick={() => onPageChange(page + 1)}>Next →</button>
    </div>
  );
}

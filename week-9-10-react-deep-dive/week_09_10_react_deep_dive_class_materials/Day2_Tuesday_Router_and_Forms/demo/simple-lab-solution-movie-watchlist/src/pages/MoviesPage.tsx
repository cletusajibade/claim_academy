import { Link } from 'react-router-dom';
import type { Movie } from '../data';

export default function MoviesPage({ movies }: { movies: Movie[] }) {
  return (
    <>
      <h1>Movies ({movies.length})</h1>
      {movies.map((m) => (
        <div className="card" key={m.id}>
          <Link to={`/movies/${m.id}`}>{m.title}</Link> ({m.year})
        </div>
      ))}
    </>
  );
}

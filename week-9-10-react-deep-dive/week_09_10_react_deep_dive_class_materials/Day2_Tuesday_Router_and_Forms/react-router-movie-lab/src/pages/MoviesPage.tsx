import { Link } from 'react-router';
import type { Movie } from '../data';

export default function MoviesPage({ movies }: { movies: Movie[] }) {
  return (
    <div>
      <h1>Movies ({movies.length})</h1>
      {movies.map((m) => (
        <div className="card" key={m.id}>
          <Link to={`/movies/${m.id}`}>{m.title}</Link>
          <p>
            {m.year} | {m.genre}
          </p>
        </div>
      ))}
    </div>
  );
}

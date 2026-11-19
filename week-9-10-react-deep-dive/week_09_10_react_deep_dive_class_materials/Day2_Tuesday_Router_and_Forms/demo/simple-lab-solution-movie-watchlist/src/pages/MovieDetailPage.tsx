import { Link, useParams } from 'react-router-dom';
import type { Movie } from '../data';

export default function MovieDetailPage({ movies }: { movies: Movie[] }) {
  const { id } = useParams();
  const movie = movies.find((m) => m.id === Number(id));

  if (!movie) {
    return <p>Movie not found. <Link to="/movies">Back to movies</Link></p>;
  }

  return (
    <div className="card">
      <h1>{movie.title}</h1>
      <p>Year: {movie.year}</p>
      <p>Genre: {movie.genre}</p>
      <Link to="/movies">← Back to movies</Link>
    </div>
  );
}

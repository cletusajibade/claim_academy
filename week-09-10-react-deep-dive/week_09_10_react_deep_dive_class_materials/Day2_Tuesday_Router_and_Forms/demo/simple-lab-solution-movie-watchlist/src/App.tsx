import { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/Layout';
import HomePage from './pages/HomePage';
import MoviesPage from './pages/MoviesPage';
import MovieDetailPage from './pages/MovieDetailPage';
import AddMoviePage from './pages/AddMoviePage';
import NotFoundPage from './pages/NotFoundPage';
import { INITIAL_MOVIES, type Movie } from './data';

export default function App() {
  const [movies, setMovies] = useState<Movie[]>(INITIAL_MOVIES);

  function addMovie(title: string, year: number, genre: string) {
    setMovies([...movies, { id: Date.now(), title, year, genre }]);
  }

  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/movies" element={<MoviesPage movies={movies} />} />
        <Route path="/movies/:id" element={<MovieDetailPage movies={movies} />} />
        <Route path="/add" element={<AddMoviePage onAdd={addMovie} />} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}

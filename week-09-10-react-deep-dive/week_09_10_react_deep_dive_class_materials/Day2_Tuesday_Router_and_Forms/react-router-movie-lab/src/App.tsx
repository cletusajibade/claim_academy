import { Route, Routes } from 'react-router';
import HomePage from './pages/HomePage';
import NotFoundPage from './pages/NotFoundPage';
import RootLayout from './components/RootLayout';
import { INITIAL_MOVIES, type Movie } from './data';
import { useState } from 'react';
import MoviesPage from './pages/MoviesPage';

function App() {
  const [movies, setMovies] = useState<Movie[]>(INITIAL_MOVIES);
  return (
    <>
      <Routes>
        <Route element={<RootLayout />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/movies" element={<MoviesPage movies={movies} />} />
          <Route path="/movies/:id" element={<MoviesPage movies={movies} />} />
          <Route path="*" element={<NotFoundPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;

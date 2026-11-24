import { useState, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';

interface Props {
  onAdd: (title: string, year: number, genre: string) => void;
}

export default function AddMoviePage({ onAdd }: Props) {
  const [title, setTitle] = useState('');
  const [year, setYear] = useState('');
  const [genre, setGenre] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  function handleSubmit(e: FormEvent) {
    e.preventDefault();

    const yearNumber = Number(year);
    if (title.trim() === '') {
      setError('Title is required.');
      return;
    }
    if (!Number.isInteger(yearNumber) || yearNumber < 1900 || yearNumber > new Date().getFullYear()) {
      setError(`Year must be between 1900 and ${new Date().getFullYear()}.`);
      return;
    }
    if (genre === '') {
      setError('Please choose a genre.');
      return;
    }

    onAdd(title.trim(), yearNumber, genre);
    navigate('/movies');
  }

  return (
    <>
      <h1>Add Movie</h1>
      <form onSubmit={handleSubmit}>
        <input placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
        <input placeholder="Year (e.g. 2014)" type="number" value={year} onChange={(e) => setYear(e.target.value)} />
        <select value={genre} onChange={(e) => setGenre(e.target.value)}>
          <option value="">-- choose a genre --</option>
          <option>Action</option>
          <option>Animation</option>
          <option>Comedy</option>
          <option>Drama</option>
          <option>Sci-Fi</option>
        </select>
        {error && <p className="error">{error}</p>}
        <button type="submit">Add Movie</button>
      </form>
    </>
  );
}

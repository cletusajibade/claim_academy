import { useState, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';

export default function AddStudentPage({ onAdd }: { onAdd: (name: string, track: string) => void }) {
  // 6. Controlled inputs: React state holds what the user types
  const [name, setName] = useState('');
  const [track, setTrack] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  function handleSubmit(e: FormEvent) {
    e.preventDefault(); // stop the browser from reloading the page

    // 7. Validation
    if (name.trim().length < 2) {
      setError('Name must be at least 2 characters.');
      return;
    }
    if (track === '') {
      setError('Please choose a track.');
      return;
    }

    onAdd(name.trim(), track);
    navigate('/students'); // 8. go to another page from code
  }

  return (
    <>
      <h1>Add Student</h1>
      <form onSubmit={handleSubmit}>
        <input placeholder="Full name" value={name} onChange={(e) => setName(e.target.value)} />

        <select value={track} onChange={(e) => setTrack(e.target.value)}>
          <option value="">-- choose a track --</option>
          <option>Java</option>
          <option>JavaScript</option>
          <option>C#/.NET</option>
        </select>

        {error && <p className="error">{error}</p>}
        <button type="submit">Add</button>
      </form>
    </>
  );
}

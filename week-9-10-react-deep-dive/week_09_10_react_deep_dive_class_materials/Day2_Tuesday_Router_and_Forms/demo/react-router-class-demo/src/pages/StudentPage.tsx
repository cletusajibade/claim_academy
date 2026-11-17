// import type { Student } from '../data';
import { INITIAL_STUDENTS } from '../data';
import { Link } from 'react-router';

function StudentPage() {
  return (
    <div>
      <h2>Students ({INITIAL_STUDENTS.length})</h2>
      {INITIAL_STUDENTS.map((s) => (
        <div className="card" key={s.id}>
          <Link to={`/students/${s.id}`}>{s.name}</Link>
        </div>
      ))}
    </div>
  );
}

export default StudentPage;

// import type { Student } from '../data';
import { type Student, INITIAL_STUDENTS } from '../data';
import { Link } from 'react-router';

interface StudentsPageProps{
  students: Student[]
}

function StudentsPage({ students }: StudentsPageProps) {
  return (
    <div>
      <h2>Students ({students.length})</h2>
      {INITIAL_STUDENTS.map((s) => (
        <div className="card" key={s.id}>
          <Link to={`/students/${s.id}`}>{s.name}</Link>
        </div>
      ))}
    </div>
  );
}

export default StudentsPage;

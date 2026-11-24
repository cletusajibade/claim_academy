import { Link } from 'react-router-dom';
import type { Student } from '../data';

export default function StudentsPage({ students }: { students: Student[] }) {
  return (
    <>
      <h1>Students ({students.length})</h1>
      {students.map((s) => (
        <div className="card" key={s.id}>
          {/* 4. Link builds a URL that contains the student's id */}
          <Link to={`/students/${s.id}`}>{s.name}</Link>
        </div>
      ))}
    </>
  );
}

import { Link, useParams } from 'react-router-dom';
import type { Student } from '../data';

export default function StudentDetailPage({ students }: { students: Student[] }) {
  // 5. useParams reads ":id" from the URL — it is always a STRING
  const { id } = useParams();
  const student = students.find((s) => s.id === Number(id));

  if (!student) {
    return <p>No student with id {id}. <Link to="/students">Back</Link></p>;
  }

  return (
    <div className="card">
      <h1>{student.name}</h1>
      <p>Track: {student.track}</p>
      <Link to="/students">← Back to list</Link>
    </div>
  );
}

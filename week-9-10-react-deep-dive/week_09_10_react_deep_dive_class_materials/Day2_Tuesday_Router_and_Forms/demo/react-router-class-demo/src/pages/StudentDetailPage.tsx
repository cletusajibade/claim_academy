import { Link, useParams } from 'react-router';
import { INITIAL_STUDENTS } from '../data';

function StudentDetailPage() {
  const { id } = useParams();
  const student = INITIAL_STUDENTS.find((s) => s.id === Number(id));

  if (!student) {
    return (
      <p>
        No student with id {id}. <Link to="/students">Back</Link>
      </p>
    );
  }
  return (
    <div className="card">
      <h2>{student.name}</h2>
      <p>Track: {student.track}</p>
      <Link to="/students">Back to list</Link>
    </div>
  );
}

export default StudentDetailPage;

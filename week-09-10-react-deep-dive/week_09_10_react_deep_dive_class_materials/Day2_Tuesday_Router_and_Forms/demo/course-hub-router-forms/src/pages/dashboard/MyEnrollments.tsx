import { useLocation } from 'react-router-dom';
import { useCourses } from '../../context/CourseContext';

export default function MyEnrollments() {
  const { enrollments, findCourse } = useCourses();
  const flash = (useLocation().state as { flash?: string } | null)?.flash;

  return (
    <div className="card">
      {flash && <div className="flash">{flash}</div>}
      <h2>My enrollments</h2>
      {enrollments.length === 0 ? (
        <p className="muted">No enrollments yet.</p>
      ) : (
        <table>
          <thead><tr><th>Course</th><th>Name</th><th>Email</th><th>Level</th></tr></thead>
          <tbody>
            {enrollments.map((en) => (
              <tr key={en.id}>
                <td>{findCourse(en.courseId)?.code}</td>
                <td>{en.fullName}</td>
                <td>{en.email}</td>
                <td>{en.experience}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

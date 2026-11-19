import { useCourses } from '../../context/CourseContext';
import { useFakeAuth } from '../../context/FakeAuthContext';

export default function Overview() {
  const { user } = useFakeAuth();
  const { courses, enrollments } = useCourses();
  return (
    <div className="card">
      <h2>Welcome back, {user}</h2>
      <p>{courses.length} courses in the catalog · {enrollments.length} enrollment(s)</p>
    </div>
  );
}

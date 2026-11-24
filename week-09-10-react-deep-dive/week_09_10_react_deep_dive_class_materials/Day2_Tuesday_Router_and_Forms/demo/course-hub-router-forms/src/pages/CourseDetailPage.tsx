import { Link, useNavigate, useParams } from 'react-router-dom';
import { useCourses } from '../context/CourseContext';

export default function CourseDetailPage() {
  const { courseId } = useParams<{ courseId: string }>(); // always a string (or undefined)
  const { findCourse } = useCourses();
  const navigate = useNavigate();
  const course = findCourse(Number(courseId));

  if (!course) {
    return (
      <div className="card">
        <h2>Course {courseId} not found</h2>
        <Link to="/courses">Back to courses</Link>
      </div>
    );
  }

  return (
    <div className="card">
      <span className="badge">{course.category}</span>
      <h1>{course.code}: {course.title}</h1>
      <p>{course.description || <span className="muted">No description.</span>}</p>
      <p className="muted">{course.credits} credits · starts {course.startDate} · {course.online ? 'Online' : 'In person'}</p>
      <div className="row">
        <button onClick={() => navigate('enroll')}>Enroll</button>
        <button className="secondary" onClick={() => navigate(-1)}>Go back</button>
      </div>
    </div>
  );
}

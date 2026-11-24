import { Link } from 'react-router-dom';
import type { Course } from '../data/types';

export default function CourseCard({ course }: { course: Course }) {
  return (
    <div className="card">
      <span className="badge">{course.category}</span>
      <h3>{course.code}: {course.title}</h3>
      <p className="muted">{course.credits} credits · starts {course.startDate} · {course.online ? 'Online' : 'In person'}</p>
      {/* Relative link would also work: to={`${course.id}`} */}
      <Link to={`/courses/${course.id}`}>View details →</Link>
    </div>
  );
}

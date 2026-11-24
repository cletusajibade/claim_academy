import { useState, type FormEvent } from 'react';
import { Navigate, useNavigate, useParams } from 'react-router-dom';
import { useCourses } from '../context/CourseContext';
import type { Enrollment } from '../data/types';
import { EMAIL_REGEX } from '../utils/validation';

/** A smaller form using one useState per field — compare with NewCoursePage. */
export default function EnrollPage() {
  const { courseId } = useParams();
  const { findCourse, enroll } = useCourses();
  const navigate = useNavigate();
  const course = findCourse(Number(courseId));

  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [experience, setExperience] = useState<Enrollment['experience']>('beginner');
  const [agree, setAgree] = useState(false);
  const [errors, setErrors] = useState<string[]>([]);

  if (!course) return <Navigate to="/courses" replace />;

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    const problems: string[] = [];
    if (fullName.trim().split(/\s+/).length < 2) problems.push('Enter your first and last name.');
    if (!EMAIL_REGEX.test(email)) problems.push('Enter a valid email address.');
    if (!agree) problems.push('You must accept the code of conduct.');
    setErrors(problems);
    if (problems.length > 0) return;

    enroll({ courseId: course!.id, fullName: fullName.trim(), email, experience });
    // Pass a one-time message to the next page through navigation state
    navigate('/dashboard/enrollments', { state: { flash: `Enrolled in ${course!.code}!` } });
  }

  return (
    <div className="card">
      <h1>Enroll in {course.code}</h1>
      {errors.length > 0 && (
        <ul className="error">{errors.map((er) => <li key={er}>{er}</li>)}</ul>
      )}
      <form className="stack" onSubmit={handleSubmit} noValidate>
        <div className="field">
          <label htmlFor="fullName">Full name</label>
          <input id="fullName" value={fullName} onChange={(e) => setFullName(e.target.value)} />
        </div>
        <div className="field">
          <label htmlFor="email">Email</label>
          <input id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <fieldset>
          <legend>Experience</legend>
          {(['beginner', 'intermediate', 'advanced'] as const).map((lvl) => (
            <label key={lvl} style={{ marginRight: 12 }}>
              <input type="radio" name="experience" value={lvl} checked={experience === lvl} onChange={() => setExperience(lvl)} /> {lvl}
            </label>
          ))}
        </fieldset>
        <label><input type="checkbox" checked={agree} onChange={(e) => setAgree(e.target.checked)} /> I accept the code of conduct</label>
        <div><button type="submit">Submit enrollment</button></div>
      </form>
      <p className="muted">Tip: log in first, otherwise the redirect to the dashboard will bounce you to /login.</p>
    </div>
  );
}

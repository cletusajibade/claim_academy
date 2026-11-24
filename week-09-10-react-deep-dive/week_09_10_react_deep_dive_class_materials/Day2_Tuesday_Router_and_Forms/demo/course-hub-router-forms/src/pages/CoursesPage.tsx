import { useSearchParams } from 'react-router-dom';
import CourseCard from '../components/CourseCard';
import { useCourses } from '../context/CourseContext';

export default function CoursesPage() {
  const { courses } = useCourses();
  // The search term lives in the URL (?q=...) so it survives refresh and can be shared
  const [searchParams, setSearchParams] = useSearchParams();
  const q = searchParams.get('q') ?? '';

  const visible = courses.filter((c) =>
    `${c.code} ${c.title}`.toLowerCase().includes(q.toLowerCase()),
  );

  return (
    <>
      <h1>Courses</h1>
      <input
        placeholder="Search courses…"
        value={q}
        onChange={(e) => setSearchParams(e.target.value ? { q: e.target.value } : {}, { replace: true })}
      />
      <p className="muted">{visible.length} course(s)</p>
      <div className="grid">
        {visible.map((c) => <CourseCard key={c.id} course={c} />)}
      </div>
    </>
  );
}

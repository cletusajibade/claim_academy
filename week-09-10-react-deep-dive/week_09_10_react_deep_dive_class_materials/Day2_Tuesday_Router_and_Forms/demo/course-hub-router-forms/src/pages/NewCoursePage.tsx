import { useState, type ChangeEvent, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCourses } from '../context/CourseContext';
import type { Category } from '../data/types';
import { validateCourse, type CourseFormValues } from '../utils/validation';

const INITIAL: CourseFormValues = {
  code: '', title: '', category: '', credits: '3', startDate: '', online: false, description: '',
};

export default function NewCoursePage() {
  const { courses, addCourse } = useCourses();
  const navigate = useNavigate();

  // One state object for all fields (controlled components)
  const [values, setValues] = useState<CourseFormValues>(INITIAL);
  const [touched, setTouched] = useState<Partial<Record<keyof CourseFormValues, boolean>>>({});
  const [submitting, setSubmitting] = useState(false);

  // Derived — recalculated every render, never stored in state
  const errors = validateCourse(values, courses);
  const isValid = Object.keys(errors).length === 0;

  // One generic change handler for input, select and textarea
  function handleChange(e: ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) {
    const { name, value, type } = e.target;
    const newValue = type === 'checkbox' ? (e.target as HTMLInputElement).checked : value;
    setValues((prev) => ({ ...prev, [name]: newValue }));
  }

  function handleBlur(e: ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) {
    setTouched((prev) => ({ ...prev, [e.target.name]: true }));
  }

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault(); // stop the browser's full-page form POST
    // Mark everything as touched so all errors show
    setTouched({ code: true, title: true, category: true, credits: true, startDate: true, description: true });
    if (!isValid) return;

    setSubmitting(true);
    await new Promise((r) => setTimeout(r, 600)); // pretend network call
    const created = addCourse({
      code: values.code.trim(),
      title: values.title.trim(),
      category: values.category as Category,
      credits: Number(values.credits),
      startDate: values.startDate,
      online: values.online,
      description: values.description.trim(),
    });
    setSubmitting(false);
    navigate(`/courses/${created.id}`);
  }

  const showError = (field: keyof CourseFormValues) =>
    touched[field] && errors[field] ? <span className="error">{errors[field]}</span> : null;
  const cls = (field: keyof CourseFormValues) => (touched[field] && errors[field] ? 'invalid' : '');

  return (
    <div className="card">
      <h1>Add a course</h1>
      <form onSubmit={handleSubmit} noValidate>
        <div className="form-grid">
          <div className="field">
            <label htmlFor="code">Course code</label>
            <input id="code" name="code" value={values.code} onChange={handleChange} onBlur={handleBlur} className={cls('code')} placeholder="JAVA-301" />
            {showError('code')}
          </div>
          <div className="field">
            <label htmlFor="title">Title</label>
            <input id="title" name="title" value={values.title} onChange={handleChange} onBlur={handleBlur} className={cls('title')} />
            {showError('title')}
          </div>
          <div className="field">
            <label htmlFor="category">Category</label>
            <select id="category" name="category" value={values.category} onChange={handleChange} onBlur={handleBlur} className={cls('category')}>
              <option value="">— choose —</option>
              <option>Backend</option>
              <option>Frontend</option>
              <option>Cloud</option>
              <option>Data</option>
            </select>
            {showError('category')}
          </div>
          <div className="field">
            <label htmlFor="credits">Credits</label>
            <input id="credits" name="credits" type="number" value={values.credits} onChange={handleChange} onBlur={handleBlur} className={cls('credits')} />
            {showError('credits')}
          </div>
          <div className="field">
            <label htmlFor="startDate">Start date</label>
            <input id="startDate" name="startDate" type="date" value={values.startDate} onChange={handleChange} onBlur={handleBlur} className={cls('startDate')} />
            {showError('startDate')}
          </div>
          <div className="field" style={{ justifyContent: 'end' }}>
            <label><input name="online" type="checkbox" checked={values.online} onChange={handleChange} /> Online course</label>
          </div>
          <div className="field full">
            <label htmlFor="description">Description ({values.description.length}/300)</label>
            <textarea id="description" name="description" rows={3} value={values.description} onChange={handleChange} onBlur={handleBlur} className={cls('description')} />
            {showError('description')}
          </div>
        </div>
        <div className="row" style={{ marginTop: 16 }}>
          <button type="submit" disabled={submitting}>{submitting ? 'Saving…' : 'Create course'}</button>
          <button type="button" className="secondary" onClick={() => { setValues(INITIAL); setTouched({}); }}>Reset</button>
        </div>
      </form>
    </div>
  );
}

import { createContext, useContext, useState, type ReactNode } from 'react';
import { SEED_COURSES } from '../data/seed';
import type { Course, Enrollment } from '../data/types';

interface CourseContextValue {
  courses: Course[];
  enrollments: Enrollment[];
  addCourse: (course: Omit<Course, 'id'>) => Course;
  enroll: (enrollment: Omit<Enrollment, 'id'>) => void;
  findCourse: (id: number) => Course | undefined;
}

const CourseContext = createContext<CourseContextValue | undefined>(undefined);

// In-memory "database" so pages can share data (tomorrow this becomes a real API).
export function CourseProvider({ children }: { children: ReactNode }) {
  const [courses, setCourses] = useState<Course[]>(SEED_COURSES);
  const [enrollments, setEnrollments] = useState<Enrollment[]>([]);

  const addCourse = (data: Omit<Course, 'id'>) => {
    const course = { ...data, id: Date.now() };
    setCourses((prev) => [...prev, course]);
    return course;
  };
  const enroll = (data: Omit<Enrollment, 'id'>) =>
    setEnrollments((prev) => [...prev, { ...data, id: Date.now() }]);
  const findCourse = (id: number) => courses.find((c) => c.id === id);

  return (
    <CourseContext.Provider value={{ courses, enrollments, addCourse, enroll, findCourse }}>
      {children}
    </CourseContext.Provider>
  );
}

export function useCourses() {
  const ctx = useContext(CourseContext);
  if (!ctx) throw new Error('useCourses must be used inside <CourseProvider>');
  return ctx;
}

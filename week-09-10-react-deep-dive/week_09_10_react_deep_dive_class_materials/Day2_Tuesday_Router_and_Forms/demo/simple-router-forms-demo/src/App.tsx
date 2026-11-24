import { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/Layout';
import HomePage from './pages/HomePage';
import StudentsPage from './pages/StudentsPage';
import StudentDetailPage from './pages/StudentDetailPage';
import AddStudentPage from './pages/AddStudentPage';
import NotFoundPage from './pages/NotFoundPage';
import { INITIAL_STUDENTS, type Student } from './data';

export default function App() {
  // The list lives here so every page can share it (passed down as props)
  const [students, setStudents] = useState<Student[]>(INITIAL_STUDENTS);

  function addStudent(name: string, track: string) {
    setStudents([...students, { id: Date.now(), name, track }]);
  }

  // 2. Routes: "when the URL is X, show component Y"
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/students" element={<StudentsPage students={students} />} />
        <Route path="/students/:id" element={<StudentDetailPage students={students} />} />
        <Route path="/add" element={<AddStudentPage onAdd={addStudent} />} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}

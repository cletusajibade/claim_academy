import { Route, Routes } from 'react-router';
import HomePage from './pages/HomePage';
import NotFoundPage from './pages/NotFoundPage';
import RootLayout from './components/RootLayout';
import StudentsPage from './pages/StudentsPage';
import StudentDetailPage from './pages/StudentDetailPage';
import { useState } from 'react';
import { INITIAL_STUDENTS, type Student } from './data';

function App() {
  const [students, setStudents] = useState<Student[]>(INITIAL_STUDENTS);
  return (
    <Routes>
      <Route element={<RootLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/students"
          element={<StudentsPage students={students} />}
        />
        <Route path="/students/:id" element={<StudentDetailPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}

export default App;

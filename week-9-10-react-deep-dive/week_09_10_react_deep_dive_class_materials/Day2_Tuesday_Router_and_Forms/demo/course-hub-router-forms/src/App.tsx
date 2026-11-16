import { Route, Routes } from 'react-router-dom';
import RootLayout from './components/RootLayout';
import RequireAuth from './components/RequireAuth';
import HomePage from './pages/HomePage';
import CoursesPage from './pages/CoursesPage';
import CourseDetailPage from './pages/CourseDetailPage';
import NewCoursePage from './pages/NewCoursePage';
import EnrollPage from './pages/EnrollPage';
import LoginPage from './pages/LoginPage';
import NotFoundPage from './pages/NotFoundPage';
import DashboardLayout from './pages/dashboard/DashboardLayout';
import Overview from './pages/dashboard/Overview';
import MyEnrollments from './pages/dashboard/MyEnrollments';
import Settings from './pages/dashboard/Settings';

/**
 * Route map
 *  /                          Home
 *  /courses                   list (supports ?q=search)
 *  /courses/new               add-course form       (static segment wins over :courseId)
 *  /courses/:courseId         details               (dynamic segment)
 *  /courses/:courseId/enroll  enrollment form
 *  /login                     fake login
 *  /dashboard                 protected, nested layout with <Outlet/>
 *     index | enrollments | settings
 *  *                          404
 */
export default function App() {
  return (
    <Routes>
      <Route element={<RootLayout />}>
        <Route index element={<HomePage />} />
        <Route path="courses">
          <Route index element={<CoursesPage />} />
          <Route path="new" element={<NewCoursePage />} />
          <Route path=":courseId" element={<CourseDetailPage />} />
          <Route path=":courseId/enroll" element={<EnrollPage />} />
        </Route>
        <Route path="login" element={<LoginPage />} />
        <Route
          path="dashboard"
          element={
            <RequireAuth>
              <DashboardLayout />
            </RequireAuth>
          }
        >
          <Route index element={<Overview />} />
          <Route path="enrollments" element={<MyEnrollments />} />
          <Route path="settings" element={<Settings />} />
        </Route>
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}

import { NavLink, Outlet } from 'react-router-dom';

// 3. Shared layout: the nav bar stays, the page inside <Outlet /> changes
export default function Layout() {
  return (
    <>
      <nav>
        <NavLink to="/" end>Home</NavLink>
        <NavLink to="/students">Students</NavLink>
        <NavLink to="/add">Add Student</NavLink>
      </nav>
      <main>
        <Outlet />
      </main>
    </>
  );
}

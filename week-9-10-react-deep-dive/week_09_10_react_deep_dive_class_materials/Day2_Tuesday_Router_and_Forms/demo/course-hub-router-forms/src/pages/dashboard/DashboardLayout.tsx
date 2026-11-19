import { NavLink, Outlet } from 'react-router-dom';

/** A nested layout: the sidebar stays, the right side changes via <Outlet/>. */
export default function DashboardLayout() {
  return (
    <div className="sidebar-layout">
      <aside className="card sidebar">
        <NavLink to="/dashboard" end>Overview</NavLink>
        <NavLink to="enrollments">My enrollments</NavLink>
        <NavLink to="settings">Settings</NavLink>
      </aside>
      <section>
        <Outlet />
      </section>
    </div>
  );
}

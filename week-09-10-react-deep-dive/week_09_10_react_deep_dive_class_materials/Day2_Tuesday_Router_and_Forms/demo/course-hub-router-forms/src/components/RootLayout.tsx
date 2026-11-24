import { NavLink, Outlet, useNavigate } from 'react-router-dom';
import { useFakeAuth } from '../context/FakeAuthContext';

export default function RootLayout() {
  const { user, logout } = useFakeAuth();
  const navigate = useNavigate();

  return (
    <>
      <nav className="topnav">
        <div className="container">
          {/* NavLink adds class="active" automatically; `end` = exact match */}
          <NavLink to="/" end>Course Hub</NavLink>
          <NavLink to="/courses" end>Courses</NavLink>
          <NavLink to="/courses/new">Add Course</NavLink>
          <NavLink to="/dashboard">Dashboard</NavLink>
          <span className="spacer" />
          {user ? (
            <>
              <span style={{ color: '#fff' }}>Hi, {user}</span>
              <button className="secondary" onClick={() => { logout(); navigate('/'); }}>Log out</button>
            </>
          ) : (
            <NavLink to="/login">Log in</NavLink>
          )}
        </div>
      </nav>
      <main className="container">
        {/* Child routes render here */}
        <Outlet />
      </main>
    </>
  );
}

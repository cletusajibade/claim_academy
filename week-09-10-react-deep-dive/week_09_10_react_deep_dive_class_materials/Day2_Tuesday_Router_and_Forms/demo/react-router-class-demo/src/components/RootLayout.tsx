import { NavLink, Outlet } from 'react-router';

function RootLayout() {
  return (
    <div>
      <nav>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/students">Students</NavLink>
      </nav>
      <main>
        <Outlet />
      </main>
      <footer>
        <p> This is the footer</p>
      </footer>
    </div>
  );
}

export default RootLayout;

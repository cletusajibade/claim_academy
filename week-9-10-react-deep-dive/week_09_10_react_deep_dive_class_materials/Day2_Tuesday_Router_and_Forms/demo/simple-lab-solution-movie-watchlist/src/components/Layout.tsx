import { NavLink, Outlet } from 'react-router-dom';

export default function Layout() {
  return (
    <>
      <nav>
        <NavLink to="/" end>Home</NavLink>
        <NavLink to="/movies">Movies</NavLink>
        <NavLink to="/add">Add Movie</NavLink>
      </nav>
      <main>
        <Outlet />
      </main>
    </>
  );
}

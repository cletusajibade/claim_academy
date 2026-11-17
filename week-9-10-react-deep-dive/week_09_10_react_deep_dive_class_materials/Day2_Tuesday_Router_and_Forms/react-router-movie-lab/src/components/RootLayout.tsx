import {NavLink, Outlet } from 'react-router';

export default function RootLayout() {
  return (
    <div>
      <nav>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/movies">Movies</NavLink>
        <NavLink to="/add">Add Movie</NavLink>
      </nav>
      <main>
        <Outlet />
      </main>
    </div>
  );
}

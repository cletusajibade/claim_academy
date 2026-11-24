# Simple Router + Forms Demo — Student Directory

A small React + TypeScript + React Router v6 app (about 150 lines of code) for live-coding in class.
The numbered comments (1–8) in the code match the build order below.

| URL | Page | Concept |
|---|---|---|
| `/` | HomePage | basic route |
| `/students` | StudentsPage | list + `<Link>` |
| `/students/:id` | StudentDetailPage | dynamic segment + `useParams` |
| `/add` | AddStudentPage | controlled form, validation, `useNavigate` |
| anything else | NotFoundPage | `path="*"` |

## Run
```bash
npm install
npm run dev     # http://localhost:5173
```

## Live-coding order
1. `main.tsx` — wrap `<App />` in `<BrowserRouter>`.
2. `App.tsx` — `<Routes>` with Home + 404 only. Visit `/` and `/abc`.
3. `components/Layout.tsx` — nav with `NavLink` + `<Outlet />`; wrap the routes in `<Route element={<Layout />}>`.
4. `data.ts` + `StudentsPage` — list students, each name is a `<Link to={`/students/${s.id}`}>`.
5. `StudentDetailPage` — `useParams()`, `Number(id)`, handle "not found" (try `/students/99`).
6. `AddStudentPage` — two controlled inputs (`value` + `onChange`).
7. Validation in `handleSubmit` with `e.preventDefault()` and an `error` state.
8. `addStudent` in `App` (state lifted up) + `navigate('/students')` after saving.

`index.css` is provided — students don't need to type it.

Note: the list resets when you refresh the page (it lives in React state). Tomorrow it comes from the Spring Boot API.

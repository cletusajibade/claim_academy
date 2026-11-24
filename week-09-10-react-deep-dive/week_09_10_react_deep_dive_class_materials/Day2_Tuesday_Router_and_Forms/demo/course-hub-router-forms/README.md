# Day 2 Demo — Course Hub (React Router v6 + Forms, TypeScript)

## What it demonstrates
- `BrowserRouter`, `Routes`, `Route`, index routes, layout routes and `<Outlet/>` (`src/App.tsx`)
- `Link` vs `NavLink` (active styling, `end`) — `src/components/RootLayout.tsx`
- Dynamic segments with `useParams` — `src/pages/CourseDetailPage.tsx`
- Query strings with `useSearchParams` — `src/pages/CoursesPage.tsx`
- Programmatic navigation `useNavigate` (incl. `navigate(-1)` and navigation `state`)
- Nested dashboard routes — `src/pages/dashboard/*`
- Protected route **preview** with fake auth — `src/components/RequireAuth.tsx` (real JWT on Day 4–5)
- 404 catch-all route `path="*"`
- Controlled forms:
  - single state object + generic change handler + touched/blur validation — `src/pages/NewCoursePage.tsx`
  - one-state-per-field + validate-on-submit — `src/pages/EnrollPage.tsx`
  - pure validation function — `src/utils/validation.ts`

## Run
```bash
npm install
npm run dev     # http://localhost:5173
```

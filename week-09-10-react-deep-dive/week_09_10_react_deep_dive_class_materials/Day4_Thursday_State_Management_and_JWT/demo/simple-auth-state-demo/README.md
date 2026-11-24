# Day 4 Live-Coding Path: State Management + JWT Login

Eight small, runnable snapshots. Live-code one stage into the next. If the live code breaks,
open the next stage folder and continue.

The React parts use the same setup students get from `npm create vite` (React 19, TypeScript, ESLint with
`eslint-plugin-react-hooks` v7). Every React stage passes `npm run lint` and `npm run build`.

## Part A: state management (React only, no backend needed)

| Stage | Folder | What changes | The lesson |
|---|---|---|---|
| A1 | `part-a-1-prop-drilling` | `useState` in `App`; tasks + 3 callbacks passed App → Main → TaskList → TaskItem | Count the components that pass props they never use |
| A2 | `part-a-2-use-reducer` | `tasksReducer.ts` with typed actions; `useReducer` in App; one `dispatch` prop | All the ways state can change, in one pure function; components say *what happened* |
| A3 | `part-a-3-context` | `TaskContext.ts` + `useTasks()` hook, `TaskProvider.tsx`; no props through Main/TaskList | Any component can read the store directly; the Header badge updates with no props |

## Part B: Spring Security + JWT (backend, built on the Day 3 `task-manager-api`)

| Stage | Folder | What changes | Try it |
|---|---|---|---|
| B1 | `part-b-1-add-security` | **Only** `spring-boot-starter-security` added to `pom.xml` | `curl -i localhost:8080/api/tasks` → **401**; browser shows Spring's login page; React app breaks |
| B2 | `part-b-2-login-endpoint` | users table + BCrypt, `AppUserDetailsService`, `JwtService`, `SecurityConfig` (stateless, CORS, `/api/auth/**` public), `POST /api/auth/login` | Login returns a token (paste it into jwt.io) but `/api/tasks` is **still 401** even with the token. Why? Nobody reads it yet! |
| B3 | `part-b-3-jwt-filter` | `JwtAuthFilter` + `.addFilterBefore(...)` | Token in `Authorization: Bearer …` → **200** |

```bash
cd part-b-3-jwt-filter
mvn spring-boot:run                 # or run TaskManagerApplication in IntelliJ
mvn test                            # each stage's tests check that stage's lesson

curl -s -X POST localhost:8080/api/auth/login -H 'Content-Type: application/json' \
     -d '{"username":"demo","password":"password123"}'
curl -i localhost:8080/api/tasks                                    # 401
curl -s localhost:8080/api/tasks -H "Authorization: Bearer <token>" # 200
```
Seeded account: **demo / password123**. Tasks are shared by all users here (per-user tasks are in the full demo).

## Part C: React login (run against stage B3)

| Stage | Folder | What changes | The lesson |
|---|---|---|---|
| C1 | `part-c-1-login-form` | Login form → `POST /auth/login`; token in `useState` in App; tasks GET adds `Authorization: Bearer ${token}` by hand | The whole flow in the fewest lines; the token is shown on screen |
| C2 | `part-c-2-auth-context` | `AuthContext` + `AuthProvider` (same pattern as A3), Axios **interceptor** adds the header, Header with **Log out** | No token props; no header code in components |

```bash
cd part-c-2-auth-context
npm install
npm run dev        # http://localhost:5173 (refuses to start if 5173 is busy)
```

After C2, open the full demos (`../task-manager-api`, `../task-manager-web`): per-user tasks,
register, a JSON 401 body, and a `useReducer` task store combined with the API.

Note: in development, React StrictMode runs effects twice, so the Network tab shows two `GET /api/tasks`. That's expected.

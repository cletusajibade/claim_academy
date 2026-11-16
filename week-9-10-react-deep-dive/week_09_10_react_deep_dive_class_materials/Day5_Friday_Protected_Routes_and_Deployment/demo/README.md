# Day 5 Demo — Protected Routes, Logout & Production Build (capstone template)

The complete Full-Stack Application V2 reference: register → login → view tasks → logout → /tasks is inaccessible.

### Frontend additions (`task-manager-web`)
| File | Role |
|---|---|
| `src/App.tsx` | route table: public-only, protected, redirects, 404 |
| `src/routes/ProtectedRoute.tsx` | `<Navigate to="/login" state={{ from }} />` or `<Outlet/>` |
| `src/routes/PublicOnlyRoute.tsx` | logged-in users skip /login and /register |
| `src/components/AppLayout.tsx` | nav bar + **Log out** (clear token → navigate to /login) |
| `src/pages/LoginPage.tsx` | redirects back to the page the user originally requested |
| `src/pages/RegisterPage.tsx` | client validation + server 400/409 errors, auto-login |
| `src/api/client.ts` | response interceptor: 401 → logout |
| `src/context/AuthContext.tsx` | auto-logout when the token expires |
| `.env.production` | API URL baked in by `npm run build` |

### Backend additions (`task-manager-api`)
- `GET /api/auth/me`
- `application-prod.properties` — everything from environment variables; `DataLoader` disabled in `prod`.

### Manual test script (do this before every commit)
1. Visit http://localhost:5173/tasks → redirected to **/login**
2. Register a new user → lands on **/tasks** (empty list)
3. Add / complete / delete a task
4. Click **Log out** → on /login. Press Back or type /tasks → still /login
5. Log in as `demo` → sees only demo's tasks
6. Refresh the page → logged out (token was in memory — expected)

### Production build preview
```bash
# frontend
cp .env.production.local.example .env.production.local   # points at localhost API
npm run build          # type-check + bundle into dist/
npm run preview        # serves dist/ on http://localhost:4173 (already in CORS allow-list)

# backend with the prod profile
mvn clean package
JWT_SECRET='a-long-random-secret-of-at-least-32-bytes!!' \
APP_CORS_ALLOWED_ORIGINS='http://localhost:4173' \
java -jar target/task-manager-api-1.0.0.jar --spring.profiles.active=prod
```
(In prod there is no demo user — register first.)

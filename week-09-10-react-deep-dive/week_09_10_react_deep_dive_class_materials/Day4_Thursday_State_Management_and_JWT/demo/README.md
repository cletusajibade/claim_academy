# Day 4 Demo — State Management + JWT Authentication

Builds on Day 3. New pieces:

### Backend (`task-manager-api`)
| File | Role |
|---|---|
| `user/AppUser`, `UserRepository` | users table (BCrypt password hashes) |
| `auth/AuthController` | `POST /api/auth/register`, `POST /api/auth/login` → `{token, username, expiresAt}` |
| `security/JwtService` | create / verify HS256 tokens (JJWT 0.12) |
| `security/JwtAuthFilter` | reads `Authorization: Bearer …` on every request |
| `security/SecurityConfig` | stateless, CSRF off, CORS, public vs protected URLs |
| `security/RestAuthenticationEntryPoint` | JSON 401 body |
| `task/*` | tasks are now owned by a user (`findAllByOwnerUsername…`) |

Seeded account: **demo / password123**. JWT secret: `app.jwt.secret` (override with env var `JWT_SECRET`).

Try it with curl:
```bash
curl -s -X POST localhost:8080/api/auth/login -H 'Content-Type: application/json' \
     -d '{"username":"demo","password":"password123"}'
curl -s localhost:8080/api/tasks                               # 401
curl -s localhost:8080/api/tasks -H "Authorization: Bearer <token>"   # 200
```
Paste a token into https://jwt.io to show the (readable, not encrypted!) payload.

### Frontend (`task-manager-web`)
| File | Role |
|---|---|
| `src/api/client.ts` | in-memory token + request interceptor adds `Authorization` |
| `src/context/AuthContext.tsx` | `login()` / `logout()`, `isAuthenticated` |
| `src/state/tasksReducer.ts` | `useReducer` state machine for tasks (typed actions) |
| `src/context/TaskContext.tsx` | Context + reducer = global task store |
| `src/components/TaskStats.tsx` | far-away component reading the same store (no prop drilling) |
| `src/App.tsx` | conditional rendering: login form vs. tasks (routes come on Day 5) |

Run: `mvn spring-boot:run` in the API, `npm install && npm run dev` in the web app.

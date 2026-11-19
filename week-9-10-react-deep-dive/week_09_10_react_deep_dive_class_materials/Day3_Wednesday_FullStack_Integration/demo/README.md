# Day 3 Demo — Full-Stack Task Manager (Spring Boot ⇄ React + TypeScript)

```
task-manager-api/   Spring Boot 3.3 · Java 17 · JPA · H2 · Validation · CORS
task-manager-web/   React 18 · TypeScript · Vite · Axios
```

## 1. Start the API
```bash
cd task-manager-api
mvn spring-boot:run            # or run TaskManagerApplication in IntelliJ
```
- API:        http://localhost:8080/api/tasks
- H2 console: http://localhost:8080/h2-console  (JDBC URL `jdbc:h2:mem:taskdb`, user `sa`)

| Method | URL | Body | Result |
|---|---|---|---|
| GET | /api/tasks | – | 200 list |
| GET | /api/tasks/{id} | – | 200 / 404 |
| POST | /api/tasks | `{title, description?, dueDate?}` | 201 / 400 |
| PUT | /api/tasks/{id} | `{title, description?, dueDate?}` | 200 / 400 / 404 |
| PATCH | /api/tasks/{id} | `{completed: true}` | 200 / 404 |
| DELETE | /api/tasks/{id} | – | 204 / 404 |

CORS is configured in `config/CorsConfig.java`; allowed origins live in `application.properties` (`app.cors.allowed-origins`).

## 2. Start the React app
```bash
cd task-manager-web
npm install
npm run dev                    # http://localhost:5173
```
The API base URL comes from `.env.development` (`VITE_API_URL`).

## Where to look during the lecture
| Concept | File |
|---|---|
| Axios instance + env base URL | `web/src/api/client.ts` |
| Service layer (GET/POST/PATCH/DELETE) | `web/src/api/taskApi.ts` |
| Loading / error / data states | `web/src/hooks/useTasks.ts`, `web/src/App.tsx` |
| Server validation errors → form | `web/src/utils/errors.ts`, `web/src/components/TaskForm.tsx` |
| Confirm before delete | `web/src/components/TaskCard.tsx` |
| CORS | `api/.../config/CorsConfig.java` |

### Break-it-on-purpose demos
1. Stop the API → the UI shows "Cannot reach the server…".
2. Remove `http://localhost:5173` from `app.cors.allowed-origins`, restart → browser console shows a CORS error; Postman still works (CORS is a *browser* rule).
3. Submit a blank title with client validation commented out → the 400 `fieldErrors.title` from Spring is displayed.

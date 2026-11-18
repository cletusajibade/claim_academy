# Day 3 Live-Coding Path — Connecting React to Spring Boot

Five small, runnable snapshots of the same React + TypeScript app. Live-code one stage into the next.
If the live code breaks, open the next stage folder and keep going.

Backend: use `../task-manager-api` (or a student's own Week 8 API). Start it first and check that
http://localhost:8080/api/tasks shows JSON.

| Stage | Folder | What it adds | Problem it solves (break it first!) |
|---|---|---|---|
| 1 | `stage-1-fetch` | `fetch` in `useEffect`, list on screen (~30 lines, one file) | "How does React get data from Spring?" |
| 2 | `stage-2-loading-error` | `loading` / `error` state, `res.ok` check, Try again | **Stop the API** → stage 1 just shows a blank page |
| 3 | `stage-3-env-axios` | `.env.development` + `VITE_API_URL`, Axios instance, `strictPort` | URL copied in many places; `fetch` doesn't throw on 404; **start the Day 2 app first** → Vite moves to 5174 → CORS |
| 4 | `stage-4-service-hook` | `types.ts`, `api/taskApi.ts`, `hooks/useTasks.ts`, `TaskList` | App.tsx doing everything: URLs, state and UI in one place |
| 5 | `stage-5-create-form` | POST via `taskApi.create`, `TaskForm`, Spring's 400 `fieldErrors` shown | "How do we save data?" Submit a blank title → server validation message |

After stage 5, open `../task-manager-web` (the full demo): every file there will look familiar.
PATCH (mark complete) and DELETE (with confirm) are left for the students' lab.

Java mapping for stage 4: component ≈ controller, `useTasks` hook ≈ service, `taskApi` ≈ client/repository.

## Run any stage
```bash
cd stage-3-env-axios
npm install
npm run dev        # http://localhost:5173 (stages 3–5 refuse to start if 5173 is busy)
```

## Break-it demos
1. **API stopped** (stage 1 vs 2): blank page vs a clear error + Try again.
2. **Port moved** (stage 1 or 2): run another Vite app first, start this one → it lands on 5174 →
   DevTools Console shows a CORS error. Same app with stage 3's `strictPort: true` refuses to start instead.
3. **Server validation** (stage 5): clicking Add with an empty box → Spring returns 400 → "Title is required" under the form.

# Week 9 — Web Programming 3 (Java Track)
React deep dive · state management · full-stack integration with Spring Boot · JWT authentication

Each day folder contains:

| File | Purpose |
|---|---|
| `*_Teaching_Notes.docx` | Instructor notes: objectives, prep checklist, talking points, demo script, lab facilitation, FAQ, answer keys, exit ticket |
| `*_Slides.pdf` | Lecture slides (16:9) |
| `*_Lab.pdf` | Student lab handout |
| `*_Homework.pdf` | Student homework (Friday = weekly project + quiz practice) |
| `demo/` | Complete, runnable class demo project(s) |

| Day | Topic | Demo project(s) |
|---|---|---|
| 1 Mon | React 18 hooks deep dive | `hooks-playground`, `lab-solution-product-explorer` |
| 2 Tue | React Router v6 & forms | `course-hub-router-forms` |
| 3 Wed | Full-stack integration (Axios, CORS, env config) | `task-manager-api` + `task-manager-web` |
| 4 Thu | Context + useReducer, Spring Security + JWT | `task-manager-api` + `task-manager-web` |
| 5 Fri | Protected routes, logout, production build | `task-manager-api` + `task-manager-web` (capstone template) |

The Task Manager evolves day by day: each day's `demo/` folder is a complete snapshot, so you can diff Day 3 → 4 → 5.

## Requirements
- Node 18+ (frontends): `npm install && npm run dev` → http://localhost:5173
- Java 17+ and Maven (APIs): `mvn spring-boot:run` → http://localhost:8080 (or run the `*Application` class in IntelliJ)
- Day 4–5 demo login: `demo` / `password123`

Stack versions: React 18.3, TypeScript 5.6, Vite 5, React Router 6, Axios 1.x, Spring Boot 3.3.5, Spring Security 6, JJWT 0.12.6, H2.

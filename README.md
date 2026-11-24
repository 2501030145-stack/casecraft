# CaseCraft — Java Crime Investigation Simulator

A Java-first, full-stack fictional investigation workspace built to match the supplied Java Lab synopsis. It includes a React interface, Spring Boot API, and a relational PostgreSQL data model.

## Syllabus coverage (required)

| Synopsis requirement | Implementation |
|---|---|
| Case management | `CaseFile`, REST CRUD/update/status endpoints |
| Person management | Abstract `Person` with `Detective`, `Suspect`, `Witness`, `Victim` subclasses |
| Evidence management | Abstract `Evidence` with physical, digital, documentary and testimonial subclasses |
| Interfaces | `Searchable`, `Analyzable`, `Verifiable` implemented by `Evidence` |
| Clue management | Clues, persisted positions, and `ClueConnection` relationships |
| Statement management | Persisted statement records and Java string-based comparison rules |
| Timeline management | Chronological `TimelineEvent` records |
| Inconsistency detection | Rule-based review prompts; never infers guilt |
| Java Collections | `ArrayList`, `LinkedList`, `HashSet`, `TreeSet` used in services |
| Exception handling | `InvalidCaseException`, `DuplicateEvidenceException`, `MissingEvidenceException`, API exception handler |
| Multithreading | Concurrent evidence and statement analysis; separate report generation task |
| Synchronization | Synchronized shared timeline cache and sorted record index |
| Modular packages | `model`, `interfaces`, `repository`, `service`, `controller`, `exception`, `threads`, `config` |

## Project structure

```text
casecraft/
├── backend/                 # Java 21 / Spring Boot REST API
│   ├── src/main/java/com/casecraft/
│   ├── src/main/resources/schema.sql
│   ├── Dockerfile
│   └── pom.xml
├── frontend/                # React + Vite + JavaScript
│   ├── src/main.jsx
│   ├── src/styles/main.css
│   └── vercel.json
└── README.md
```

## Run locally

Prerequisites: Java 21, Maven 3.9+, Node.js 20+ and npm.

### 1. Start the Java API

```bash
cd backend
mvn spring-boot:run
```

The default development database is an embedded H2 file database. The API listens on `http://localhost:8080`; health check: `http://localhost:8080/api/health`. A fictional demonstration case is inserted automatically only when the database is empty.

### 2. Start the React frontend

In another terminal:

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

Open the Vite URL (normally `http://localhost:5173`). The frontend calls `http://localhost:8080/api` by default.

### 3. Production PostgreSQL configuration

Set these environment variables for the Java service:

```env
DATABASE_URL=jdbc:postgresql://HOST:5432/DATABASE?sslmode=require
DB_USERNAME=YOUR_DATABASE_USER
DB_PASSWORD=YOUR_DATABASE_PASSWORD
DB_DRIVER=org.postgresql.Driver
DDL_AUTO=update
PORT=8080
CORS_ALLOWED_ORIGINS=https://YOUR-VERCEL-DOMAIN.vercel.app
```

Use the JDBC URL form, not a provider's `postgres://` URL. Keep credentials in hosting environment variables; never commit them. `schema.sql` is included as a readable reference schema. Hibernate creates/updates the mapped schema on startup when `DDL_AUTO=update`.

## Deployment

### Frontend — Vercel

1. Push this project to GitHub.
2. In Vercel, import the repository.
3. Set **Root Directory** to `frontend`.
4. Build command: `npm run build`.
5. Output directory: `dist`.
6. Add environment variable `VITE_API_URL=https://YOUR-JAVA-API-HOST/api`.
7. Deploy.

The included `frontend/vercel.json` supports client-side route fallback.

### Backend — Render (Docker)

1. Create a PostgreSQL database on Render or use a PostgreSQL provider.
2. Create a new Web Service from the same GitHub repository.
3. Set the service root directory to `backend` and choose Docker as runtime (the included Dockerfile builds the Spring Boot JAR).
4. Set the PostgreSQL and port environment variables above.
5. Deploy and verify `/api/health`.
6. Update Vercel's `VITE_API_URL` to the deployed API URL plus `/api`, then redeploy the frontend.

The Java API is intentionally hosted separately: Vercel is serving the React static frontend, not running a persistent Spring Boot server.

## API routes

| Method | Route | Purpose |
|---|---|---|
| GET | `/api/health` | Health check |
| GET / POST | `/api/cases` | List / create cases |
| GET / PUT / DELETE | `/api/cases/{id}` | Read details / update / delete case |
| POST | `/api/cases/{id}/people` | Add person |
| POST | `/api/cases/{id}/evidence` | Add evidence |
| PATCH | `/api/evidence/{id}/verification` | Mark evidence verified/unverified |
| POST | `/api/cases/{id}/clues` | Add clue |
| PUT | `/api/clues/{id}/position` | Save clue position |
| POST | `/api/clues/connect` | Persist clue relationship |
| POST | `/api/cases/{id}/statements` | Add statement |
| POST | `/api/cases/{id}/events` | Add timeline event |
| DELETE | `/api/people/{id}`, `/api/evidence/{id}`, `/api/clues/{id}`, `/api/statements/{id}`, `/api/events/{id}` | Delete an individual record |
| POST | `/api/cases/{id}/analyze` | Run concurrent analysis tasks |
| GET | `/api/cases/{id}/report` | Generate structured report data |

## Current functional scope and honest limitations

- The application is a **fictional simulator**. The supplied synopsis does not call for real crime reporting, live crime maps, actual address intelligence, or real-world safety scoring. Those ideas are not represented as real services here.
- The crime-scene screen is an interactive SVG floor-plan prototype with placeable markers and a CSS 3D spatial preview, not a photorealistic 3D game engine.
- The evidence board supports persisted clue placement and persisted clue-to-clue connections. Its initial sample notes are illustrative only; create actual clues to persist them.
- Evidence reference URLs are metadata references. Binary file upload/storage is not yet wired to an object-storage provider.
- Reports export as structured JSON; a formatted PDF export can be added as a follow-on.
- The current analysis uses transparent predefined heuristics and review prompts. It is not an AI verdict system and does not establish guilt, deception, or factual truth.
- Authentication, role-based access control, production rate limiting, and audit trails are not included in this deadline-oriented starter and should be added before handling sensitive or public data.

## Before submission

1. Run the API and frontend locally.
2. Verify all modules using the included demonstration case.
3. Create a new case and test each create/update flow.
4. Refresh the browser to confirm persistence.
5. Run analysis and export the report.
6. Configure real hosting secrets and test the deployed API from the Vercel domain.

## Push to GitHub

From the project directory:

```bash
git init
git add .
git commit -m "Build CaseCraft investigation simulator"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/casecraft.git
git push -u origin main
```

Do not commit `.env` files, database credentials, `node_modules`, Maven `target`, or local H2 database files. The `.gitignore` is configured for these.

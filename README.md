# Task Manager API

A production-ready RESTful **Task Manager API** built with **Java 21** and **Spring Boot**.

This project demonstrates clean architecture, validation, error handling, database migrations, automated tests, and containerized deployment.

---

## Production (Render)

- **Base URL:** https://task-manager-api-njza.onrender.com
- **Health (Actuator):** https://task-manager-api-njza.onrender.com/actuator/health
- **Tasks (example):** https://task-manager-api-njza.onrender.com/tasks?page=0&size=5&sort=id,desc

> **Note:** Render Free may have a **cold start** (~50s) on the first request.

---

## API Overview

### Main endpoints (`/tasks`)

- `POST /tasks` — create task
- `GET /tasks` — list tasks (paginated)  
  - supports: `page`, `size`, `sort`, `q`, `status`, `priority`
- `GET /tasks/{id}` — get by id
- `PUT /tasks/{id}` — update (full)
- `PATCH /tasks/{id}` — partial update
- `DELETE /tasks/{id}` — delete

### Health

- `GET /actuator/health`

---

## CORS

CORS is configured to allow requests from the GitHub Pages frontend:

- https://gustavomprado.github.io

---

## Tech Stack

- Java 21
- Spring Boot 3 (Web, Validation, Data JPA)
- PostgreSQL
- Flyway (migrations)
- Spring Boot Actuator
- OpenAPI / Swagger (SpringDoc)
- H2 (tests)
- JUnit 5 & Mockito
- Docker & Docker Compose
- Gradle

---

## Running locally (Docker Compose)

From the folder where `docker-compose.yml` is located:

~~~bash
docker compose up --build
~~~

The API will be available at:
- http://localhost:8081

Health:
- http://localhost:8081/actuator/health

To stop:

~~~bash
docker compose down
~~~

---

## Repositories

- Backend: https://github.com/GustavoMPrado/task-manager-api
- Frontend: https://github.com/GustavoMPrado/task-manager-frontend

---

## Contact

Gustavo Marinho Prado Alves  
GitHub: https://github.com/GustavoMPrado



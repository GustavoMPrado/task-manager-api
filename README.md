# Task Manager API (Spring Boot)

API REST de tarefas (CRUD) feita em **Java 21 + Spring Boot**, com validação, paginação, tratamento global de erros e documentação via **Swagger/OpenAPI**.  
Em “prod”, roda com **PostgreSQL + Flyway** via **Docker Compose**.

## Stack
- Java 21
- Spring Boot (Web, Validation, Data JPA)
- PostgreSQL (Docker)
- Flyway (migrations)
- OpenAPI/Swagger (springdoc)
- Gradle
- Tests (JUnit + MockMvc)

## Como rodar com Docker (recomendado)
Pré-requisito: Docker Desktop

Na raiz do projeto:

```powershell
docker compose up --build
```

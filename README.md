# Bowling API (Spring Boot)

This project exposes a CRUD API for a single resource (Bowling) using Spring Boot, Spring Data JPA, and a RestController.

By default the project uses an in-memory H2 database for development and tests (so it runs out-of-the-box). To run against PostgreSQL (for persistence), follow the instructions below.

## Run with PostgreSQL (recommended for production / assignment)

1) Start PostgreSQL (Docker):

```bash
# starts postgres on localhost:5432 with a 'bowling' database
docker run --name bowling-db \
	-e POSTGRES_USER=postgres \
	-e POSTGRES_PASSWORD=postgres \
	-e POSTGRES_DB=bowling \
	-p 5432:5432 -d postgres:15
```

2) Build the project:

```bash
./mvnw -DskipTests clean package
```

3) Run with the `prod` profile so the application uses `application-prod.properties` (Postgres):

```bash
SPRING_PROFILES_ACTIVE=prod java -jar target/Spring-Lesson-0.0.1-SNAPSHOT.jar
```

The app will start on port `1234` by default.

## Endpoints
Base: `http://localhost:1234/api/v1/bowling`

- GET /api/v1/bowling — list all bowlers
- POST /api/v1/bowling — create (JSON body: name, email, average)
- GET /api/v1/bowling/{id} — get single
- PUT /api/v1/bowling/{id} — update (full replace)
- DELETE /api/v1/bowling/{id} — delete

## Notes
- Validation errors return 400 with a JSON map of field -> message.
- Duplicate email returns 409 Conflict.
- Missing resource returns 404 Not Found.

If you want me to also add Flyway migrations or an example `docker-compose.yml` for Postgres + the app, tell me and I can add them.

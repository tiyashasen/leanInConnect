# Lean In Connect — Momentum Feed

**Application deployed:** https://leaninconnect-production.up.railway.app/

**Source Code:** https://github.com/tiyashasen/leanInConnect

A full-stack Lean In Connect prototype focused on a small but meaningful habit: sharing career wins. The experience helps members see progress, celebrate it together, and discover the next resource or conversation that can keep their momentum going.

## Stack and architecture

| Layer | Choice | Responsibility |
| --- | --- | --- |
| Frontend | HTML, CSS, browser JavaScript | Responsive feed, modal interaction, client UI state and optimistic rendering |
| API | Java 17 + Spring Boot | REST endpoints, input validation, CORS and application/service structure |
| Data | Spring Data JPA + H2 | Persisted `CareerWin` records, ordered newest-first |

The frontend and API intentionally run as separate processes. This makes the contract visible and keeps the backend ready to evolve independently (authentication, moderation, relational database, and observability) without coupling it to UI delivery.

```text
Browser UI → GET/POST /api/wins → Spring Boot → JPA → H2
```

Each module has one responsibility. The web layer never talks directly to JPA; it delegates to the service. This keeps HTTP concerns, domain data, business logic, and persistence replaceable and easy to test independently.

## Run locally

### 1. Start the Spring Boot backend

Install Java 17+ and Maven, then from `backend` run:

```bash
cd backend
mvn spring-boot:run
```

The API is available at `http://localhost:8080/api/wins`. H2 persists data locally under `backend/data`.

Open `http://localhost:8080`.

## API contract

### `GET /api/wins`

Returns saved career wins, newest first.

### `POST /api/wins`

Creates a career win. The request is validated server-side.

```json
{ "message": "I asked for the scope I wanted and heard yes." }
```

## Design and technical decisions

## Prototype Scope

This prototype focuses on the core "Momentum Feed" experience—allowing users to view community wins and share their own accomplishments through a fully functional Spring Boot backend.

The Home section is implemented end-to-end, while the remaining navigation items (Circles, Learn, and Networks) are included as representative navigation for future platform expansion. They are intentionally presented as part of the overall product experience but are outside the scope of this implementation.

I focused on the home feed because it is where community, encouragement, and personal career growth meet. The editorial hero makes the mission feel human, the feed provides social proof, and the side rail turns momentum into a next step.

I chose Spring Boot because the core interaction benefits from a clear layered API: controller, request validation, service, repository, and persistence. H2 keeps the take-home simple to run while demonstrating a real database-backed boundary. Browser JavaScript owns transient UI state (modal visibility, form text, loading/error feedback); the Spring API owns persisted domain state.


### Experience chosen and why

The prototype is a **Momentum Feed** for Lean In Connect. It makes a career win visible to a trusted Circle and gives other members practical next actions through relevant learning resources. This supports the mission by making career growth feel social, concrete, and achievable rather than solitary.

### What is real vs. prototype content

The career-win flow is real: creating a win sends a validated request to the Spring Boot API, saves it through JPA into H2, and returns it to the client. The hero, Circle details, featured posts, reactions, resource cards, and progress meter are intentionally static prototype content so the assignment stays focused on one complete end-to-end slice.

### Key tradeoffs

- I used H2 instead of PostgreSQL to make the project easy to run in a short take-home timebox. In the deployed environment, data may be reset when the application restarts. For production, I would replace H2 with PostgreSQL and persistent storage.
- I used plain JavaScript rather than a larger frontend framework to emphasize the UI experience without unnecessary setup. It still owns client-side state for the modal, character counter, request lifecycle, and user feedback.

### Next steps

With more time, I would add authenticated users, PostgreSQL with Flyway migrations, roles and Circle membership, reactions/comments, API integration tests, structured observability, moderation/rate limiting, and a deployment pipeline.

## With more time

- Add authenticated member identities rather than the prototype author.
- Use PostgreSQL and migrations (Flyway) for a production datastore.
- Add unit/integration tests, rate limiting, moderation, and structured logs.
- Move the API URL to deployment-specific environment configuration.
- Accessibility improvements
- Integration testing
- CI/CD pipeline

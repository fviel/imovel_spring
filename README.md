🚀 1. Build the Project
### 1.1. From terminal
´´´./mvn clean install´´´

### 1.2. Or on Windows:
mvnw.cmd clean install

▶️ 2. Run the Application

### 2.1 From terminal
./mvn spring-boot:run

### 2.2. By JRE
java -jar target/your-app-name.jar


---------
### Docker command 
docker run --name pgamitta -e POSTGRES_PASSWORD=1822 -e POSTGRES_USER=sa_amitta -p 5432:5432 -v pgdata:/var/lib/postgresql/data -d postgres:17-alpine

---
# to run everything
docker-compose up --build


---

Absolutely, Fernando! Here's a complete `README.md` tailored to your Spring Boot + PostgreSQL + Docker setup, with support for `dev` and `prod` profiles, testing instructions, and Postman usage:

---

```markdown
# 🏡 Real State API

A Spring Boot RESTful API for managing real estate properties using PostgreSQL, Docker, and UUID-based entities.

---

## 📦 Requirements

- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- Postman (optional, for testing)

---

## 🚀 Running the Project

### 🔧 1. Clone the Repository

```bash
git clone https://github.com/your-username/real_state.git
cd real_state
```

### 🧪 2. Run Tests

```bash
./mvnw test
```

---

## 🧱 Build the Application

```bash
./mvnw clean package
```

This will generate a `.jar` file in the `target/` directory.

---

## 🌱 Development Environment

### ▶️ Run with Docker Compose (dev profile)

```bash
docker-compose --env-file .env up --build
```

This will:
- Start a PostgreSQL container (`pgamitta`)
- Build and run the Spring Boot app with `dev` profile
- Expose the API at `http://localhost:8080/realstates`

---

## 🏭 Production Environment

To run with the `prod` profile:

```bash
SPRING_PROFILES_ACTIVE=prod docker-compose --env-file .env up --build
```

Or set the profile in your `.env` file:

```env
SPRING_PROFILES_ACTIVE=prod
```

---

## 🧪 API Testing with Postman

1. Open Postman and create a new collection.
2. Use the following endpoints:

| Method | Endpoint                  | Description         |
|--------|---------------------------|---------------------|
| GET    | `/realstates`             | List all properties |
| GET    | `/realstates/{id}`        | Get by UUID         |
| POST   | `/realstates`             | Create new property |
| PUT    | `/realstates/{id}`        | Update property     |
| DELETE | `/realstates/{id}`        | Delete property     |

3. Example POST body:
```json
{
  "address": "123 Main St",
  "price": 250000.0,
  "description": "Charming 3-bedroom home"
}
```

4. Set `Content-Type: application/json` in headers.

---

## 🧪 Test Coverage

- ✅ Valid and invalid input handling
- ✅ UUID format validation
- ✅ CRUD operations
- ✅ Integration tests using `MockMvc`

---

## 📁 Profiles

| Profile | Description                     |
|---------|---------------------------------|
| `dev`   | Enables SQL logging, auto-DDL   |
| `prod`  | Disables SQL logs, validates DDL|

---

## 🧩 Environment Variables (.env)

```env
POSTGRES_HOST=pgamitta
POSTGRES_PORT=5432
POSTGRES_DB=postgres
POSTGRES_USER=sa_amitta
POSTGRES_PASSWORD=1822
SPRING_PROFILES_ACTIVE=dev
```

---

## 🛠️ Build & Run Manually (without Docker)

```bash
./mvnw spring-boot:run
```

Or:

```bash
java -jar target/real_state-0.0.1-SNAPSHOT.jar
```

---

## 📬 Feedback

Feel free to open issues or contribute with pull requests!

```

---

Let me know if you'd like me to generate a Postman collection file (`.json`) to import directly, or add Swagger/OpenAPI docs for interactive testing.

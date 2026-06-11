# highlevel-interview-be

Spring Boot backend service.

## Tech Stack

- Java 17
- Spring Boot 4.0.6 (Web MVC, Data JPA, Validation)
- H2 in-memory database
- Hazelcast
- Lombok
- Maven (wrapper included)

## Getting Started

### Prerequisites

- JDK 17+

### Run

```bash
./mvnw spring-boot:run
```

The application starts on [http://localhost:8080](http://localhost:8080).

### Build

```bash
./mvnw clean package
```

### Test

```bash
./mvnw test
```

## Health Check

```bash
curl http://localhost:8080/health
# OK
```

## Database

This service uses an **in-memory H2 database**. Data is reset every time the app restarts.

### H2 Console

The H2 web console is enabled. With the app running, open:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Connect using the credentials below.

| Setting       | Value                  |
| ------------- | ---------------------- |
| JDBC URL      | `jdbc:h2:mem:appdb`    |
| Driver Class  | `org.h2.Driver`        |
| User Name     | `sa`                   |
| Password      | _(empty)_              |
| Dialect       | `org.hibernate.dialect.H2Dialect` |

> Note: The JDBC URL `jdbc:h2:mem:appdb` must match exactly in the console for it to connect to the running app's database.

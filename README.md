# Depense — Expense Service

Manages user **expenses** for Money Minder MS. Validates the owner through the
user service, persists expenses, and publishes a notification event when one is
created.

## Tech stack
Java · Spring Boot · Spring Data JPA · MySQL · Spring Cloud (Eureka, Config) · RabbitMQ (AMQP) · WebClient · Bean Validation · Lombok

## API — base path `/api/depenses` · port `8081`
| Method | Path | Action |
| --- | --- | --- |
| POST | `/api/depenses` | Create an expense |
| GET | `/api/depenses` | List all |
| GET | `/api/depenses/{id}` | Get by id |
| PUT | `/api/depenses/{id}` | Update |
| DELETE | `/api/depenses/{id}` | Delete |

## Data & messaging
MySQL `gestion_depenses` (port 3307) · publishes expense events over RabbitMQ · calls the user service via WebClient.

## Run
```bash
mvn spring-boot:run
```

## Configuration
Settings live in `src/main/resources/application.properties`. Override the database and RabbitMQ credentials with environment variables in any shared or production environment — do not commit real secrets.

## Part of Money Minder MS

One service of the **Money Minder MS** Spring Cloud platform. Full architecture and
the other services: [API-Gateway README](https://github.com/Money-Minder-MS/API-Gateway).
Start EurekaServer (8761) and Config-Server (8099) first.


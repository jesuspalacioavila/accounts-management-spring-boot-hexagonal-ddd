# cuentas

Microservicio de cuentas (Spring Boot 3.3.5, Java 17, hexagonal, MySQL).

## Endpoints
- `POST /api/accounts` body `{"userId":"..."}` -> 201
- `GET /api/accounts/{id}`
- `GET /api/accounts/user/{userId}`
- Swagger: `/swagger-ui.html`

## Variables de entorno
`PORT` (8081), `DB_HOST`, `DB_PORT` (3306), `DB_NAME` (cuentas_db), `DB_USERNAME`, `DB_PASSWORD`, `DB_SSL_MODE` (DISABLED local / REQUIRED en Aiven)

## Correr local
Copia `application-local.properties.example` a `application-local.properties`, llénalo y:
`.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=local"`

# java_final_project

### URL Shortener Project

This project is a URL shortening service built with Spring Boot, PostgreSQL, and Redis. It allows users to shorten long URLs and manage them efficiently.

---
## Technologies Used

- **Java 17**
- **Spring Boot 2.7.5**
- **Spring Data JPA**
- **Spring Data Redis**
- **PostgreSQL**
- **Redis**
- **Docker & Docker Compose**
- **Swagger**

## Getting Started

### Prerequisites

Make sure you have the Docker installed on your machine:

Configuration
The application uses environment variables for configuration. These variables are defined in the .env file. Update the .env file with your configuration if needed:

```
POSTGRES_DB=url_db
POSTGRES_USER=user
POSTGRES_PASSWORD=00000000
REDIS_PORT=6379
```

### Starting 

```
git clone https://github.com/owenowenisme/java_final_project.git
cd java_final_project
docker-compose up
```

The application will be available at http://localhost:8080/api/v1.

### Testing

http://localhost:8080/swagger-ui/index.html

### Stoping

```
docker-compose down
```
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew

RUN ./gradlew bootJar


CMD ["java", "-jar", "build/libs/springboot-shorturl-v1.jar"]

# CMD [ "printenv" ]

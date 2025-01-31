FROM openjdk:22-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY src ./src

COPY pom.xml .

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]
FROM openjdk:22-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y telnet && apt-get install -y maven

COPY src ./src

COPY pom.xml .

COPY target/witProject-0.0.1-SNAPSHOT.jar /path/to/your/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
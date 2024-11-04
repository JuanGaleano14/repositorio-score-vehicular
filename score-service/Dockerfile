FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY --from=build /app/target/score-service-0.0.1-SNAPSHOT.jar score-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "score-service.jar"]
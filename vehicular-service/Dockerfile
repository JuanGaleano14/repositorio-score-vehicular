FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY --from=build /app/target/vehicular-service-0.0.1-SNAPSHOT.jar vehicular-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "vehicular-service.jar"]
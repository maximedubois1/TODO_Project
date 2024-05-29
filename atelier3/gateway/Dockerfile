# Étape 1 : Construction du JAR
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image finale
FROM openjdk:17-jdk-alpine

VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

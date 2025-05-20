# Multi-stage Dockerfile for building and running Quarkus Java project on Render

# Stage 1: Build the application using Maven
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src src

# Make mvnw executable
RUN chmod +x mvnw

# Build the application in native mode or JVM mode (here using package)
RUN ./mvnw package -DskipTests -Dquarkus.package.type=uber-jar

# Debugging: List contents of /app and /app/target to verify jar presence
RUN ls -l /app
RUN ls -l /app/target

# Stage 2: Create runtime image
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the built jar from the build stage using wildcard to handle jar name variations
COPY --from=build /app/target/*.jar app.jar

# Expose the default Quarkus port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

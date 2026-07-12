# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (caching)
COPY backend/pom.xml ./backend/
RUN mvn -f backend/pom.xml dependency:go-offline

# Copy all backend source and build
COPY backend ./backend
RUN mvn -f backend/pom.xml clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR
COPY --from=build /app/backend/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
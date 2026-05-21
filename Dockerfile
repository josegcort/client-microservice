# ---------- BUILD ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml first to use dependency caching
COPY pom.xml .

# Download dependencies (this will be cached)
RUN mvn -B -U dependency:resolve dependency:resolve-plugins

# Copy source code
COPY src ./src

# Compile the project, generating static files and jar file
RUN mvn clean install -DskipTests

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the build JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
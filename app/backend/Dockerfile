# Use official Java 17 base image for building
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Run Maven build (if mvnw not found, fallback to mvn)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# ===============================
# Run stage
# ===============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

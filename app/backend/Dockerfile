# Use an official Java 17 image (Render supports Temurin)
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the project (Render will cache this)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Copy only the generated JAR to the final image
# (Optional optimization)
RUN mkdir -p target && cp target/*.jar app.jar

# Expose port 8080 (Render expects this)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

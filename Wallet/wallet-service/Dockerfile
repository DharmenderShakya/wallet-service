# ---------- Stage 1: Build the application using Maven ----------
FROM maven:3.9.2 AS builder

WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Verify that the JAR file is created (optional but useful for debugging)
RUN ls -al /app/target/

# ---------- Stage 2: Run the application using Java 17 ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built JAR from the Maven build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose your application port
EXPOSE 8383

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

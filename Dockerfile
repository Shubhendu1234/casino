# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/casino.jar app.jar

# Expose port
EXPOSE 8082

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

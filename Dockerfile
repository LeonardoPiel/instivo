# Use the official OpenJDK 17 image from Docker Hub
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container at /app.jar
COPY instivotesting/target/instivotesting-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app will run on (e.g., 8085)
EXPOSE 8085

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

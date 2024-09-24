# Use a base image that includes Java (OpenJDK)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/quizservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your application will run
EXPOSE 8090

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

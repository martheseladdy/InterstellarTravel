# Use a base image with Java already installed
FROM openjdk:20

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY /build/libs/interstellar-travel-0.0.1-SNAPSHOT.jar /app/app.jar
# Expose the port that the Spring Boot application listens on
EXPOSE 8080

# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
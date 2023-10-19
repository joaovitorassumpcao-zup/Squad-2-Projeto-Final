# Use the official OpenJDK 11 base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/StudyGoals-0.0.1-SNAPSHOT.jar /app/StudyGoals-0.0.1-SNAPSHOT.jar

# Set the command to run your application
CMD ["java", "-jar", "/app/StudyGoals-0.0.1-SNAPSHOT.jar"]

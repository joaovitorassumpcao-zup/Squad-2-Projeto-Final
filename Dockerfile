# ---- Build Stage ----
FROM maven:3.8.2-jdk-11-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the project source
COPY src src/

# Package the application
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/StudyGoals-0.0.1-SNAPSHOT.jar /app/StudyGoals-0.0.1-SNAPSHOT.jar

# Set the command to run your application
CMD ["java", "-jar", "/app/StudyGoals-0.0.1-SNAPSHOT.jar"]

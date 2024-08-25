# Build stage
#FROM gradle:8.8.0-jdk22-alpine AS build
FROM openjdk:21
WORKDIR /app

# Copy all required files
#COPY build.gradle settings.gradle gradlew gradle /app/
#COPY gradle /app/gradle
#COPY src /app/src
COPY build/libs/WhenTakenSlackBot-1.0-SNAPSHOT.jar app.jar

#RUN ls -la /app
#RUN pwd
# Build the project
#RUN ./gradlew build

# Runtime stage
#FROM openjdk:22

#WORKDIR app

# Copy the JAR from the build stage
#COPY --from=build /app/build/libs/WhenTakenSlackBot-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
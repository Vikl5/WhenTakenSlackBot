# Ktor docs for Dockerfile: https://ktor.io/docs/docker.html#prepare-docker
# Stage 1: Build the application
FROM gradle:8.8.0-jdk21 AS build
ENV TZ=Europe/Paris
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle shadowJar --no-daemon

# Stage 2: Create a lightweight image to run the application
FROM openjdk:21-jdk-slim
ENV TZ=Europe/Paris
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
COPY --from=build /app/build/libs/WhenTakenSlackBot-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

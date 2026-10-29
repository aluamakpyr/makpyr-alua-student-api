# ---------- Stage 1: build (official JDK + Gradle wrapper) ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon -x test

# ---------- Stage 2: runtime (Java runtime only) ----------
FROM eclipse-temurin:21-jre
LABEL maintainer="Alua Makpyr" group="IT2-2306" app="makpyr-alua-student-api"
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

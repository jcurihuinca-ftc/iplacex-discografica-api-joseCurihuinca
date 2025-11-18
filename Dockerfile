# ---------- STAGE 1: BUILD ----------
FROM gradle:9.2.0-jdk21 AS build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle clean bootJar --no-daemon

# ---------- STAGE 2: RUN ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
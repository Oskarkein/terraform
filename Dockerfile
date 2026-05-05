# Stage 1: BUILD (opcional, ya que Jenkins compila)
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x gradlew && ./gradlew --no-daemon clean bootJar -x test -Dorg.gradle.jvmargs="-Xmx256m"

# Stage 2: RUNTIME
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar JAR compilado
COPY --from=builder /app/build/libs/*.jar app.jar

# Usuario no-root
RUN useradd -m -u 1000 spring && chown -R spring:spring /app
USER spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# Sử dụng OpenJDK 21 làm base image
FROM openjdk:21-jdk-slim

# Copy file JAR của ứng dụng vào Docker container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Mở cổng 8080 (hoặc cổng mà ứng dụng Spring Boot sử dụng)
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]

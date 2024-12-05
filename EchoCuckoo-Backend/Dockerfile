# Sử dụng OpenJDK 21 làm base image
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc trong Docker container
WORKDIR /app

# Copy file JAR của ứng dụng vào Docker container
COPY target/MyTimeStoreBackEnd-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 (hoặc cổng mà ứng dụng Spring Boot sử dụng)
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

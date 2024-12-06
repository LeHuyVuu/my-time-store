# Bước 1: Sử dụng một image có Maven để build ứng dụng
FROM maven:3.9.0-openjdk-21-slim as build

# Đặt thư mục làm việc trong container build
WORKDIR /app

# Sao chép file pom.xml và source code vào container để build
COPY ./EchoCuckoo-Backend/pom.xml ./pom.xml
COPY ./EchoCuckoo-Backend/src ./src

# Chạy Maven để build ứng dụng và tạo tệp JAR
RUN mvn clean install -DskipTests

# Bước 2: Sử dụng OpenJDK để chạy ứng dụng
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc trong Docker container
WORKDIR /app

# Sao chép tệp JAR từ container build vào container chạy ứng dụng
COPY --from=build /app/target/MyTimeStoreBackEnd-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 (hoặc cổng mà ứng dụng Spring Boot sử dụng)
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

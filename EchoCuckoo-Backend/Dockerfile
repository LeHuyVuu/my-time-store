# Bước 1: Sử dụng một image có Maven để build ứng dụng
FROM maven:3.9.0-openjdk-21-slim as build

# Đặt thư mục làm việc trong container build


# Bước 2: Sử dụng OpenJDK để chạy ứng dụng
FROM openjdk:21-jdk-slim
EXPOSE 8080
# Đặt thư mục làm việc trong Docker container
ADD target/MyTimeStoreBackEnd-0.0.1-SNAPSHOT.jar MyTimeStoreBackEnd-0.0.1-SNAPSHOT.jar

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/MyTimeStoreBackEnd-0.0.1-SNAPSHOT.jar"]

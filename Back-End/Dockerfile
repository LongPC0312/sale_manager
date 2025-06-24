# Sử dụng Java base image
FROM openjdk:17-jdk-slim

# Tạo thư mục làm việc trong container
WORKDIR /app

# Copy file JAR từ máy vào container
COPY target/sale-manager-0.0.1-SNAPSHOT.jar app.jar

# Mở port 8080 nếu bạn dùng Spring Boot mặc định
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]

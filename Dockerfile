# Используйте базовый образ с Java 17 (OpenJDK)
FROM eclipse-temurin:17-jdk-jammy

# Установите рабочую директорию внутри контейнера
WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

# Etapa 1: build com Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: criar imagem com JAR gerado
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/toDoApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

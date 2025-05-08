# Etapa 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . .

# Executa o build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia o JAR gerado na etapa de build para o contêiner final
COPY --from=builder /app/target/app_saude-0.0.1-SNAPSHOT.jar app_saude.jar

EXPOSE 8080

CMD ["java", "-jar", "app_saude.jar"]

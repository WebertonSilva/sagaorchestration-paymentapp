# syntax=docker/dockerfile:1

FROM eclipse-temurin:20-jdk-jammy AS paymentservice

# Diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR do seu aplicativo para o contêiner
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 8082

# Comando para executar o aplicativo Java (substitua com sua classe principal)
CMD ["./mvnw", "spring-boot:run"]

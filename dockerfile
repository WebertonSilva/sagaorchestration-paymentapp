# syntax=docker/dockerfile:1

FROM eclipse-temurin:20-jdk-jammy AS paymentservice

# Diretório de trabalho no contêiner
WORKDIR /app

# Atualize o gerenciador de pacotes e instale dependências necessárias
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    unzip \
    python3 \
    python3-pip \
    groff \
    less \
    && rm -rf /var/lib/apt/lists/*


# Link python3 to python
RUN ln -s /usr/bin/python3 /usr/bin/python

# Instale o AWS CLI
RUN pip3 install --no-cache-dir awscli

# Verifique a instalação do AWS CLI
RUN aws --version

# Copie o arquivo JAR do seu aplicativo para o contêiner
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

EXPOSE 8082

# Comando para executar o aplicativo Java (substitua com sua classe principal)
CMD ["./mvnw", "spring-boot:run"]

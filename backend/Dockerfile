# Usar uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR para o container
COPY target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

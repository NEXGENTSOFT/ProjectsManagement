# Usa una imagen base con OpenJDK 17
FROM openjdk:17-jdk-slim

# Instala Maven
RUN apt-get update && apt-get install -y maven && apt-get clean && rm -rf /var/lib/apt/lists/*

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Maven
COPY pom.xml /app/

# Copia el código fuente de la aplicación
COPY src /app/src

# Construye el proyecto con Maven
RUN mvn clean install -DskipTests

# Define el comando de inicio
CMD ["java", "-jar", "target/projectsmanagement-0.0.1-SNAPSHOT.jar"]

# Expon la aplicación en el puerto 8080
EXPOSE 8080

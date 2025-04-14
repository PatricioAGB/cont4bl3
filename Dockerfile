# Imagen base de Eclipse Temurin JDK 21
FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado por Gradle
COPY build/libs/Cont4bl3-*.jar app.jar

# Expone el puerto de la app
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

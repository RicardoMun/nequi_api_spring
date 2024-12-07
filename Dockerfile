#IMAGEN
FROM eclipse-temurin:23.0.1_11-jdk

EXPOSE 8080

#DIRECTORIO
WORKDIR /root

#COPIAR ARCHIVOS EN EL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#DEPENDENCIAS
RUN ./mvnw dependency:go-offline

#CODIGO
COPY ./src /root/src

#CONSTRUIR APP
RUN ./mvnw clean install -DskipTests

#LEVANTAR APP
ENTRYPOINT ["java", "-jar", "/root/target/SpringDocjer-0.0.1-SNAPSHOT.jar"]

LABEL authors="richi"

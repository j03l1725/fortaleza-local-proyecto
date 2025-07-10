# fortaleza-local-proyecto/Dockerfile
# Este es el plano de construcción para la APLICACIÓN, no para el bastion.

# Usamos la imagen base de Tomcat.
FROM tomcat:10.1-jdk17-temurin

# Limpiamos las aplicaciones por defecto.
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos nuestro archivo .war, que contiene la aplicación Java.
COPY target/fortaleza-app.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos el puerto de Tomcat.
EXPOSE 8080

# El comando para iniciar el servidor Tomcat.
CMD ["catalina.sh", "run"]

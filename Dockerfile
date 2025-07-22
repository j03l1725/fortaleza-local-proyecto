# fortaleza-local-proyecto/Dockerfile

FROM tomcat:10.1-jdk17-temurin

# --- AÑADIR ESTA LÍNEA ---
# Instala curl para permitir las pruebas de salud (health checks).
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/fortaleza-app.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

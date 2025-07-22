<div align="center">

🛡️ Fortaleza Local v2 🛡️
Simulación de una Arquitectura de Nube Segura y Automatizada

</div>

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Badge"/>
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker Badge"/>
<img src="https://img.shields.io/badge/Terraform-7B42BC?style=for-the-badge&logo=terraform&logoColor=white" alt="Terraform Badge"/>
<img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL Badge"/>
<img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white" alt="Nginx Badge"/>
</p>

Índice
Descripción del Proyecto

Diagrama de Arquitectura

Arsenal Tecnológico

Instrucciones de Ejecución

Entorno de Desarrollo (Docker Compose)

Despliegue Simulado (Terraform)

Conceptos de Nube Demostrados

1. Descripción del Proyecto
Este proyecto es una demostración práctica de cómo desplegar una arquitectura web de tres niveles (Gateway, Aplicación, Base de Datos) de forma segura, persistente y reproducible, aplicando los principios de la Computación en la Nube en un entorno local.

El objetivo es demostrar el dominio de Infraestructura como Código (IaC), Contenerización y patrones de seguridad de red. La infraestructura es gestionada de forma declarativa con Terraform, los servicios se ejecutan en contenedores Docker aislados y la orquestación del entorno de desarrollo se maneja con Docker Compose.

La aplicación es un servicio web Java (Servlet) que se conecta a una base de datos PostgreSQL para registrar y mostrar un contador de visitas, demostrando así una arquitectura con estado y persistencia de datos.

2. Diagrama de Arquitectura
El siguiente diagrama ilustra la arquitectura final de 3 niveles. El contenedor Nginx actúa como el único punto de entrada, recibiendo el tráfico y redirigiéndolo a la aplicación. La aplicación, a su vez, es la única que puede comunicarse con la base de datos PostgreSQL. Tanto la aplicación como la base de datos están completamente aisladas del exterior.

<p align="center">
<img src="diagrama_v2.svg" alt="Diagrama de Arquitectura v2" width="90%">
</p>

3. Arsenal Tecnológico
Tecnología

Rol en el Proyecto

Material de Estudio Relevante

☕ Java / Servlet

Desarrollo de la lógica de negocio de la aplicación (Contador de visitas).

Semana 12: Servelet

🐘 PostgreSQL

Sistema de gestión de base de datos para la persistencia de datos.

Semana 6: Dockers (Contenerización de BD)

🐳 Docker

Plataforma de contenerización para empaquetar y aislar cada servicio.

Semana 6: Dockers

🐋 Docker Compose

Orquestación del entorno de desarrollo local multi-contenedor.

Semana 6: Dockers

🚀 Nginx

Implementado como Reverse Proxy para actuar como Gateway de seguridad.

Semana 9: Seguridad en la nube

Terraform

Herramienta de IaC para el aprovisionamiento de la infraestructura de despliegue.

Semana 11: Infraestructura como código

🐙 Git y GitHub

Sistema de control de versiones y plataforma de hospedaje de código.

Semana 10: Automatización de los DevOps

4. Instrucciones de Ejecución
4.1. Entorno de Desarrollo (con Docker Compose)
Este es el método recomendado para pruebas rápidas y desarrollo.

Clonar el Repositorio:

git clone https://github.com/j03l1725/fortaleza-local-proyecto.git
cd fortaleza-local-proyecto

Construir y Levantar la Arquitectura:
Este único comando construirá la imagen de la aplicación y lanzará los tres contenedores en el orden correcto.

docker-compose up --build

Verificar el Funcionamiento:
Abre tu navegador o usa curl para acceder a la aplicación. El contador de visitas debe aumentar con cada petición.

curl http://localhost/

Detener y Limpiar el Entorno:

docker-compose down

4.2. Despliegue Simulado (con Terraform)
Este método simula un despliegue de producción gestionado por IaC.

Construir la Imagen de la Aplicación:
Asegúrate de que el archivo .war esté generado (mvn clean package) y luego construye la imagen Docker.

docker build -t fortaleza-app:1.0 .

Desplegar la Infraestructura con Terraform:

cd infra
terraform init
terraform apply -auto-approve

Verificar la Implementación:

Prueba de acceso público (debe funcionar):

curl http://localhost/

Prueba de acceso directo a la app (debe fallar):

curl http://localhost:8080/

Destruir la Infraestructura:

terraform destroy -auto-approve

5. Conceptos de Nube Demostrados
Este proyecto simula los siguientes conceptos de seguridad y arquitectura de nube:

Arquitectura de 3 Niveles: La separación de responsabilidades en Gateway (Nginx), Lógica (Java App) y Datos (PostgreSQL) es un pilar de las arquitecturas robustas.

Red Privada (VPC): Simulada mediante una red Docker personalizada (fortaleza-net), que crea un perímetro de red aislado.

Aislamiento de Recursos (Subredes Privadas): Tanto la aplicación como la base de datos no exponen puertos al host, simulando recursos en subredes privadas.

Gateway y Control de Acceso: El contenedor Nginx actúa como nuestro Gateway controlado. El aislamiento de red de Docker simula un grupo de seguridad que solo permite el tráfico desde el proxy hacia la aplicación y desde la aplicación hacia la base de datos.

Persistencia de Datos: El uso de volúmenes de Docker demuestra cómo se gestionan los datos con estado en un entorno de contenedores efímeros.

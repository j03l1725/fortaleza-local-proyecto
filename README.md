# 🛡️ Fortaleza Local v2 🛡️
### Simulación de una Arquitectura de Nube Segura y Automatizada

<div align="center">

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Terraform](https://img.shields.io/badge/Terraform-7B42BC?style=for-the-badge&logo=terraform&logoColor=white)](https://www.terraform.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)](https://www.nginx.com/)

</div>

---

## 📋 Índice
* [Descripción del Proyecto](#-descripción-del-proyecto)
* [Diagrama de Arquitectura](#️-diagrama-de-arquitectura)
* [Arsenal Tecnológico](#️-arsenal-tecnológico)
* [Instrucciones de Ejecución](#-instrucciones-de-ejecución)
  * [Entorno de Desarrollo (Docker Compose)](#entorno-de-desarrollo-con-docker-compose)
  * [Despliegue Simulado (Terraform)](#despliegue-simulado-con-terraform)
* [Conceptos de Nube Demostrados](#️-conceptos-de-nube-demostrados)

---

## 📝 Descripción del Proyecto
Este proyecto es una demostración práctica de cómo desplegar una arquitectura web de tres niveles (**Gateway**, **Aplicación**, **Base de Datos**) de forma segura, persistente y reproducible, aplicando los principios de la Computación en la Nube en un entorno local.

El objetivo es demostrar el dominio de **Infraestructura como Código (IaC)**, **Contenerización** y patrones de seguridad de red. La infraestructura es gestionada de forma declarativa con **Terraform**, los servicios se ejecutan en contenedores **Docker** aislados y la orquestación del entorno de desarrollo se maneja con **Docker Compose**.

La aplicación es un servicio web Java (Servlet) que se conecta a una base de datos PostgreSQL para registrar y mostrar un contador de visitas, demostrando así una arquitectura con estado y persistencia de datos.

---

## 🏗️ Diagrama de Arquitectura
El siguiente diagrama ilustra la arquitectura final de 3 niveles. El contenedor **Nginx** actúa como el único punto de entrada, recibiendo el tráfico y redirigiéndolo a la aplicación. La aplicación, a su vez, es la única que puede comunicarse con la base de datos **PostgreSQL**. Tanto la aplicación como la base de datos están completamente aisladas del exterior.

<p align="center">
  <img src="diagrama.svg" alt="Diagrama de Arquitectura v2" width="90%">
</p>

---

## 🛠️ Arsenal Tecnológico

| Tecnología | Rol en el Proyecto |
| :--- | :--- | 
| ☕ **Java / Servlet** | Desarrollo de la lógica de negocio de la aplicación (Contador de visitas). |
| 🐘 **PostgreSQL** | Sistema de gestión de base de datos para la persistencia de datos. |
| 🐳 **Docker** | Plataforma de contenerización para empaquetar y aislar cada servicio. |
| 🐋 **Docker Compose** | Orquestación del entorno de desarrollo local multi-contenedor. |
| 🚀 **Nginx** | Implementado como Reverse Proxy para actuar como Gateway de seguridad. |
| 🌍 **Terraform** | Herramienta de IaC para el aprovisionamiento de la infraestructura. |
| 🐙 **Git y GitHub** | Sistema de control de versiones y plataforma de hospedaje de código. |

---

## 🚀 Instrucciones de Ejecución

### Entorno de Desarrollo (con Docker Compose)
Este es el método recomendado para pruebas rápidas y desarrollo.

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/j03l1725/fortaleza-local-proyecto.git](https://github.com/j03l1725/fortaleza-local-proyecto.git)
    cd fortaleza-local-proyecto
    ```

2.  **Construir y Levantar la Arquitectura:**
    Este único comando construirá la imagen de la aplicación y lanzará los tres contenedores en el orden correcto.
    ```bash
    docker-compose up --build
    ```

3.  **Verificar el Funcionamiento:**
    Abre tu navegador o usa `curl` para acceder a la aplicación. El contador de visitas debe aumentar con cada petición.
    ```bash
    curl http://localhost/
    ```

4.  **Detener y Limpiar el Entorno:**
    ```bash
    docker-compose down
    ```

### Despliegue Simulado (con Terraform)
Este método simula un despliegue de producción gestionado por IaC.

1.  **Construir la Imagen de la Aplicación:**
    Asegúrate de que el archivo `.war` esté generado (`mvn clean package`) y luego construye la imagen Docker.
    ```bash
    docker build -t fortaleza-app:1.0 .
    ```

2.  **Desplegar la Infraestructura con Terraform:**
    ```bash
    cd infra
    terraform init
    terraform apply -auto-approve
    ```

3.  **Verificar la Implementación:**
    * Prueba de acceso público (debe funcionar):
        ```bash
        curl http://localhost/
        ```
    * Prueba de acceso directo a la app (debe fallar):
        ```bash
        curl http://localhost:8080/
        ```

4.  **Destruir la Infraestructura:**
    ```bash
    terraform destroy -auto-approve
    ```

---

## ☁️ Conceptos de Nube Demostrados
Este proyecto simula los siguientes conceptos de seguridad y arquitectura de nube:

* **Arquitectura de 3 Niveles:** La separación de responsabilidades en Gateway (Nginx), Lógica (Java App) y Datos (PostgreSQL) es un pilar de las arquitecturas robustas.
* **Red Privada (VPC):** Simulada mediante una red Docker personalizada (`fortaleza-net`), que crea un perímetro de red aislado.
* **Aislamiento de Recursos (Subredes Privadas):** Tanto la aplicación como la base de datos no exponen puertos al host, simulando recursos en subredes privadas.
* **Gateway y Control de Acceso:** El contenedor Nginx actúa como nuestro Gateway controlado. El aislamiento de red de Docker simula un grupo de seguridad que solo permite el tráfico desde el proxy hacia la aplicación y desde la aplicación hacia la base de datos.
* **Persistencia de Datos:** El uso de volúmenes de Docker demuestra cómo se gestionan los datos con estado en un entorno de contenedores efímeros.

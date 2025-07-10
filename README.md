# Proyecto: Fortaleza Local

## 1. Descripción del Proyecto

Este proyecto es una demostración práctica de los principios de **Infraestructura como Código (IaC)**, **Contenerización** y **Arquitectura de Red Segura**, implementados en un entorno local para simular un despliegue en la nube.

El objetivo es desplegar una aplicación web Java (Servlet) de forma segura y automatizada, protegiéndola del acceso directo externo a través de un patrón de Reverse Proxy. Toda la infraestructura es gestionada de forma declarativa con **Terraform** y los servicios se ejecutan en contenedores **Docker** aislados.

---

## 2. Diagrama de Arquitectura

![Diagrama de Arquitectura](https://raw.githubusercontent.com/j03l1725/fortaleza-local-proyecto/refs/heads/main/diagrama.svg)
---

## 3. Tecnologías Utilizadas

| Tecnología | Rol en el Proyecto | Material de Estudio Relevante |
| :--- | :--- | :--- |
| **Java (OpenJDK 17)** | Lenguaje de programación para la aplicación web de backend. | `Semana 12: Servelet` |
| **Maven** | Herramienta para la construcción y empaquetado de la aplicación Java. | `Semana 12: Servelet` |
| **Docker** | Plataforma de contenerización para empaquetar y aislar la aplicación y el proxy. | `Semana 6: Dockers` |
| **Terraform** | Herramienta de IaC para definir y gestionar la infraestructura local (contenedores, redes). | `Semana 11: Infraestructura como código` |
| **Nginx** | Implementado como un Reverse Proxy para actuar como punto de entrada seguro. | `Semana 9: Seguridad en la nube` (Concepto de Gateway) |
| **Git y GitHub** | Sistema de control de versiones y plataforma de hospedaje de código. | `Semana 10: Automatización de los DevOps` |

---

## 4. Instrucciones de Ejecución

Para desplegar y verificar la "Fortaleza Local" en un sistema Ubuntu con las herramientas requeridas instaladas, sigue estos pasos:

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/TU_USUARIO/fortaleza-local-proyecto.git](https://github.com/TU_USUARIO/fortaleza-local-proyecto.git)
    cd fortaleza-local-proyecto
    ```

2.  **Construir la Aplicación Java:**
    ```bash
    mvn clean package
    ```

3.  **Construir la Imagen Docker de la Aplicación:**
    ```bash
    docker build -t fortaleza-app:1.0 .
    ```

4.  **Desplegar la Infraestructura con Terraform:**
    ```bash
    cd infra
    terraform init
    terraform apply -auto-approve
    ```

5.  **Verificar la Implementación:**
    * **Prueba de acceso público (debe funcionar):**
      ```bash
      curl http://localhost/health
      ```
    * **Prueba de acceso directo (debe fallar):**
      ```bash
      curl http://localhost:8080/health
      ```

6.  **Destruir la Infraestructura:**
    ```bash
    terraform destroy -auto-approve
    ```

---

## 5. Simulación de Conceptos de Nube

Este proyecto simula los siguientes conceptos de seguridad y arquitectura de nube en un entorno local:

* **Red Privada Virtual (VPC):** Simulada mediante una red Docker personalizada (`fortaleza-net`), que crea un perímetro de red aislado para nuestros servicios.
* **Subred Privada:** El contenedor de la aplicación (`fortaleza-app-container`) no expone puertos al host, simulando un recurso en una subred privada que no es accesible desde internet.
* **Gateway / Balanceador de Carga:** El contenedor **Nginx** actúa como nuestro punto de entrada controlado (Gateway), recibiendo todo el tráfico y distribuyéndolo internamente. Es el único componente expuesto al "público" (la máquina host).
* **Control de Acceso (Grupos de Seguridad):** El aislamiento de red de Docker, junto con la configuración del Reverse Proxy, simula un grupo de seguridad que solo permite el tráfico desde el proxy hacia la aplicación, bloqueando el resto.

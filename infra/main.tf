# fortaleza-local-proyecto/infra/main.tf

resource "docker_image" "nginx_image" {
  name = "nginx:1.25-alpine"
}
resource "docker_image" "app_image" {
  name = "fortaleza-app:1.0"
}

resource "docker_network" "fortaleza_net" {
  name = "fortaleza-net"
}

# 1. Contenedor Nginx (Reverse Proxy)
resource "docker_container" "nginx_proxy" {
  name  = "nginx-proxy"
  image = docker_image.nginx_image.name

  ports {
    internal = 80
    external = 80
  }

  volumes {
    host_path      = abspath("../nginx/nginx.conf")
    container_path = "/etc/nginx/conf.d/default.conf"
    read_only      = true
  }

  networks_advanced {
    name = docker_network.fortaleza_net.name
  }
}

# 2. Contenedor Aplicación (Privado)
resource "docker_container" "app" {
  name  = "fortaleza-app-container"
  image = docker_image.app_image.name

  networks_advanced {
    name = docker_network.fortaleza_net.name
  }
}

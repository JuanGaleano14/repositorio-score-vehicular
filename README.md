# Demo Sofka - Vehicular Score Checker
``Elaborado por: Juan Pablo Galeano Salguero``

Este proyecto es una aplicación que permite consultar el puntaje de un vehículo a través de su placa. Está compuesto por dos microservicios (vehicular y score) y un frontend, todo ello gestionado con RabbitMQ para la comunicación asincrónica.

## Requisitos

Antes de desplegar la aplicación, se deben de tener instalados los siguientes requisitos:

- [Docker](https://www.docker.com/get-started) (versión 20.10 o superior)
- [Docker Compose](https://docs.docker.com/compose/install/) (versión 1.27 o superior)
- **Máquina virtual o Windows con Hyper-V activado**: Es necesario tener Hyper-V activado si estás utilizando Docker Desktop en Windows.

## Instalación

En el directorio repositorio-score-vehicular se encuentran los 3 proyectos junto con el docker-compose.yml.

Se debe abrir una consola y ejecutar el comando:

- docker-compose up --build

Tener en cuenta que los microservicios esperan a que RabbitMQ termine de iniciar por completo, lo cual puede tomar un momento.

## Acceder a la aplicación

- El frontend estará disponible en **http://localhost**

Al iniciar la aplicación se mostrará un campo de texto para ingresar la placa. Solo se encuentra registrada una placa **MIY690**.

Esta placa tiene los parámetro enviados en la descripción de la prueba, por lo cual su puntaje será 62. Si desea agregar más placas y puntajes por año, puede dirigirse al microservicio **vehicular-service** en la ruta:

- src/main/java/com/demo/sofka/infrastructure/adapter/out/database/VehiculoRepositoryImpl.java

Allí se encuentra el arreglo DATA_VEHICULO con los datos de las placas.

## Descripción de los Servicios

- **vehicular-service:** Este microservicio consulta la información del vehículo basado en la placa proporcionada.
- **score-service:** Este microservicio calcula el puntaje del vehículo.
- **frontend:** La interfaz de usuario construida con Angular.
- **rabbitmq:** Mensajería entre microservicios.

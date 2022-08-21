# Country traveler - simple graph path finding app
__Author:__ Jan Hrbotický

## Basic information
This simple Spring Boot application loads country data from source JSON and exposes 
one endpoint `GET /routing/SOURCE/DESTINATION` which finds shortest path
in graph between two nodes using BFS algorithm.

### Used technologies
* Java 17
* Spring Boot 2.5.7
* Maven
* Docker / Docker-compose
* OpenAPI
* Lombok
* JUnit 5 + Mockito

## Installing the app
### __Prerequisities__

* Installed [Docker](https://www.docker.com/)

### __Instalation__

1. Checkout this repository
1. Start up docker
1. Create ```.env``` file.
    * It is recommended to use ```.env.example``` as an example (```cp .env.example .env```). In case you do not want to use this file, you have to provide following variables
        * _SERVER_PORT_ - Port where server will accept connections
1. Run ```docker-compose up``` command
1. __Thats it!__ Application is running on ```localhost:8090``` (In case you have not changed the port of an app)
    * Swagger OpenAPI v3 is reachable on address ```http://localhost:8090/swagger-ui/index.html```
1. To stop application, simply run ```docker-compose down```
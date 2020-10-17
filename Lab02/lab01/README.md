# Lab01

## Descripción

## Objetivo

* Crear proyectos usando: 
    * https://start.spring.io/
    * maven
      ```
      mvn archetype:generate -DgroupId=com.example -DartifactId=lab01b -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
      ```          

* Spring boot:
    * Arquitectura (Controller, Service, Repository, Entity y Dto)
    * Crear test cases para Controller

* Crear test cases:
    * Controller
    * Crear test cases para Controller
    
## Tarea
* 23-11 Crear 2 unit test para los métodos del controller web:
    * POST /customers
    * PUT /customers/{id}
    * DELETE /customers/{id}


## Prerequisites
**Software**
* Java SDK 11.x or higher
* Maven 3.x or higher
* Docker 17.12.x or higher
* Docker Compose: 1.18.x or higher
* IntelliJ IDEA

## Pub/sub
**Software**
clientes.created.*
clientes.created.putted (clientes.crear,auditoria)
clientes.created.processing (auditoria)
clientes.created.success (notifications,auditoria)
clientes.created.failed (auditoria)
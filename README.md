# Angular 7 + Spring boot 2.1 microservices + PostgreSQL 10

## Application

This example show application for keeping records of courses, students and teachers.

## Architecture
The application is divided into two basic sections, a backend (Spring boot 2.1 microservices) and a frontend (Angular 7). 

![architecture](link)

## Backend

Backend side is composed of spring boot applications that are organized according to microservice architecture.  
There are a few microservices:

* faculty
* report
* config
* eureka
* zuul

### Faculty  
This microservice provides basic create, read, update, and delete (CRUD) operations.  
Faculty microservice use [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview) to store and retrieve data in a 
relational database ([PostgreSQL](https://www.postgresql.org/)) with provided pagination for GET method.  

[Lombok](https://www.baeldung.com/intro-to-project-lombok) is a library which facilitates many tedious tasks and it reduce 
Java source code verbosity (no need for getters, setters and constructors).  
[Swagger](https://swagger.io/) offers the most powerful and easiest to use tools to take full advantage of the OpenAPI Specification 
(document, test).  
![swagger](link)

### Report  
This microservice generate a report and comunicate with faculty microservice.  
This communication is easily implemented using [feign](https://cloud.spring.io/spring-cloud-openfeign/spring-cloud-openfeign.html).  
Client-side load balancing is provided by [ribbon](https://www.baeldung.com/spring-cloud-rest-client-with-netflix-ribbon) 
(for multiple instances of faculty miscroservice).  
The report can be generated as pdf, saved or printed thanks to [jasper](https://community.jaspersoft.com/).

### Config  
This microservice ([Spring Cloud Config](https://spring.io/projects/spring-cloud-config#overview)) provides server and client-side support for externalized configuration in a distributed system.
It is a central location for managing external microservice properties in all environments. 
Properties can be stored in a local file, a git repository or a git cloud repository such as GitHub. 

### Eureka  
This microservice ([Client-side service discovery](https://www.baeldung.com/spring-cloud-netflix-eureka)) allows microservices to find and communicate with each other without hard-coding hostname and port.
Each microservice is registered with this microservice during startup.  
![eureka](link)

### Zuul  
This microservice ([Zuul Api Gateway](https://spring.io/guides/gs/routing-and-filtering/)) is a gateway application that handles all the requests and performs dynamic routing of microservice applications.


## Frontend
```shell
ng serve -o
```


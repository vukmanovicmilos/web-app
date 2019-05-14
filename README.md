# Angular 7 + Spring boot 2.1 microservices + PostgreSQL 10

## Table of contents
* [Application](#application)
* [Backend](#backend)
* [Frontend](#frontend)
* [Running](#running)
* [Screenshots](#screenshots)
* [Directions for further development](#directions-for-further-development)

## Application

This example show application for keeping records of courses, students and teachers.  


## Architecture
The application is divided into two basic sections, a backend (Spring boot 2.1 microservices) and a frontend (Angular 7). 

![](frontend/src/assets/background.png?raw=true)  

Relational database schema:  

![](frontend/src/assets/schema.jpg?raw=true?style=centerme)

## Backend

Backend side is composed of spring boot applications that are organized according to microservice architecture.  
There are a few microservices:

* faculty
* report
* config
* eureka
* zuul

Each microservice can be built as a [Docker](https://www.docker.com/) image and assembled so that with one command it is possible to create and run all microservices from the configuration.  

### Faculty  
This microservice provides basic create, read, update, and delete (CRUD) operations.  
Faculty microservice use [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview) to store and retrieve data in a 
relational database ([PostgreSQL](https://www.postgresql.org/)) with provided pagination for GET method.  
[Lombok](https://www.baeldung.com/intro-to-project-lombok) is a library which facilitates many tedious tasks and it reduce 
Java source code verbosity (no need for getters, setters and constructors).  
[Swagger](https://swagger.io/) offers the most powerful and easiest to use tools to take full advantage of the OpenAPI Specification 
(document, test).  

![](frontend/src/assets/6.PNG?raw=true)

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

![](frontend/src/assets/7.PNG?raw=true)

### Zuul  
This microservice ([Zuul Api Gateway](https://spring.io/guides/gs/routing-and-filtering/)) is a gateway application that handles all the requests and performs dynamic routing of microservice applications.


## Frontend

Frontend side is Angular 7 application with [Angular Material](https://material.angular.io/) theming with animations and some extra things such as open an external link in new tab (for backend administration), embeded pdf file... Also, sorting, filtering and pagination is provided (pagination on the frontend and backend).  

![](frontend/src/assets/1.PNG?raw=true)

## Running

Requirement:

* [PostgreSQL 10](https://www.postgresql.org/download/) 
* [Spring Tool Suite 4](https://spring.io/tools) 
* [nodejs](https://nodejs.org/en/download/) 
* [Visual Studio Code](https://code.visualstudio.com/download)  

To start the backend, start the microservices from STS in the order shown on the application architecture.  
To start the frontend, run the following commands in VSC:

```shell
npm install
ng serve -o
```
You will also need to provide some CORS plugin for your browser.  

## Screenshots

**[Report generated from app](frontend/src/assets/report.pdf)**   

![](frontend/src/assets/1.PNG?raw=true) 

![](frontend/src/assets/2.PNG?raw=true) 

![](frontend/src/assets/3.PNG?raw=true) 

![](frontend/src/assets/4.PNG?raw=true) 

![](frontend/src/assets/5.PNG?raw=true) 

![](frontend/src/assets/6.PNG?raw=true) 

![](frontend/src/assets/7.PNG?raw=true)  

## Directions for further development  
#### In progress:

* Spring security
* Spring profiles
* AWS

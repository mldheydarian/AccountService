# Account Service
This is a Java based Account Service which consists an API set for Adding Different Accounts including Current and Saving as well getting Customer Accounts.
All the defined specification are done completely

#### Design:
I have used Domain Driven Design Approach to making an extensible and clean architecture. so tha it could be work in Microservice environment with minimum effort.
I have tried to decouple subdomain as Account Domain, Transaction Domain and Customer Domain.
An orchestrator layer is designed between API and Domains for handling application request and orchestrating decoupled domain service

#### Layers:
API (Rest Controllers)
Application(Applications Services and ExceptionHandlers)
Domain: Domain-Models , Domain Service, Data Repository
Infrastructure: Configurations

#### Used Frameworks and Technologies:
Spring Boot - Spring Web - Spring Data - SpringBootTest, Hibernate - Maven - Log4J2 , H2 db, Swagger - lombok -flyWay and etc


## Build and Run Project
In order to start the project please follow the Instructions below
1- Open the Project in IntelliJ Idea (Or any other IDE) this project is developed in IntelliJIdea
2- Build and Run sourceCode via Maven (There is no need External Db because of using an embedded H2 database)
3- Use Provided PostMan Collection or Swagger UI for testing the functionality of APIs (http://localhost:8080/swagger-ui.html)

## Outstanding Features and Interesting Specifications in Implementation:
there are many good Ideas that have made this project extensible, scalable like:
#### 1- Domain Driven Design (each subdomain is working in separate package )
#### 2- Solid and OOP based Design with a Clean and understandable Code
#### 3- Multi Layers Structure with decoupled subdomains
#### 4- Unit and Integration tests for API, Application and Domain Services (because they are a risky layer)
#### 5- Having Customised Exception Handlers For Api set
#### 6- Comprehensive Logging into Console and LogFile for All layers.
#### 7- Simple UI for testing APIS via Swagger.
#### 8- In memory Database.

* note: To get an overview of the structure and architecture please have a look at the Design and Implementation.pdf file inside Zip file.

### Hope it will be satisfying :)
#### Feel free to ask any question regarding running the project.
#### Arman.heydarian@gmail.com

Best Regards.

# SpringBoot+JPA+H2+Mockito+Maven - Microservice Project

This is a sample Java / Maven / Spring Boot (version 2.0.4) application that can be used as a starter for creating a spring boot microservice.

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or any server container installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by below command:
```
    mvn spring-boot:run
```
Once the application runs you should see something like this

```
2019-02-14 09:35:43 - Tomcat started on port(s): 8080 (http) with context path ''
2019-02-14 09:35:43 - Started SpringBootRestApiApplication in 23.384 seconds (JVM running for 43.995)
2019-02-14 09:35:43 - --Application Started--
```

## About the Service

The service is just a simple Receipe review REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.foodapp.springrest.controller.ReceipeController``` on **port 8080**. 

Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations 

Here are some endpoints you can call:

### Get information about system health, configurations, etc.

```
get : http://localhost:8080/api/receipes
post : http://localhost:8080/api/save
post : http://localhost:8080/api/receipes
```
### Create a Receipe resource

```
POST /api/save
Accept: application/json
Content-Type: application/json

[
   {
      "title":"Crock Pot Caramelized Onions",
      "href":"http://www.recipezaar.com/Crock-Pot- Caramelized-Onions-191625",
      "ingredients":[
         "butter",
         "onions"
      ],
      "thumbnail":"http://img.recipepuppy.com/ 338845.jpg"
   },
   {
      "title":"Pulled Chicken Sandwiches (Crock Pot)",
      "href":"http://www.recipezaar.com/Pulled-Chicken- Sandwiches-Crock-Pot-242547",
      "ingredients":[
         "chicken",
         "onions"
      ],
      "thumbnail":"http://img.recipepuppy.com/ 107122.jpg"
   },
   {
      "title":"Bruschetta With Roasted Garlic and Cherry Tomatoes",
      "href":"http://www.recipezaar.com/Bruschetta-With- Roasted-Garlic-and-Cherry-Tomatoes-244281",
      "ingredients":[
         "garlic",
         "italian bread"
      ],
      "thumbnail":"http://img.recipepuppy.com/ 199304.jpg"
   },
   {
      "title":"Garlic Vinegar",
      "href":"http://www.recipezaar.com/Garlic- Vinegar-251602",
      "ingredients":[
         "garlic",
         "vinegar"
      ],
      "thumbnail":"http://img.recipepuppy.com/ 647882.jpg"
   }
]

RESPONSE: HTTP 201 (Created)
```

### Retrieve a all list of receipes

```
http://localhost:8080/api/receipes/

Response: HTTP 200
Content: list 
```

### Retrieve a receipe resource based on ingredients

```
POST /api/receipes
Accept: application/json
Content-Type: application/json

{  
   "ingredients":[  
      "onions",
      "rice",
      "vinegar"
   ]
}

RESPONSE: List of Receipes
```


# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. 

### To view your H2 in-memory datbase

To view and query the database you can browse to http://localhost:8080/h2-console. Default username is 'sa' with a blank password. Refer the applicatio.properties for H2 DB file name. Make sure you disable this in your production profiles. 

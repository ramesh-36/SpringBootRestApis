Spring Boot Rest Jpa Eample:
===========================
Rest Endpoints:

Create Receipe Resource:
========================
POST:  localhost:8080/api/save

Accept: application/json
Content-Type: application/json

{ "rid":1,
    "title": "Spaghetti with Clams & Corn",
    "href": "http://www.eatingwell.com/recipes/ spaghetti_clams_corn.html",
    "ingredients": [
       {	"iid":1,
       "name":"onions"
    }
    ],
    "thumbnail": "http://img.recipepuppy.com/ 698569.jpg"
  }

Retrieve Receipe Data:
=====================
GET: http://localhost:8080/api/receipies

GET : localhost:8080/api/receipe/2

REtrive INgredients Data:
=========================
GET : http://localhost:8080/api/ingredients

DB Configuration for MySQL:
==========================
application.properties
-----------------------

## Database Properties
spring.datasource.url = jdbc:mysql://localhost:3306/ramesh?useSSL=false
spring.datasource.username = root
spring.datasource.password = root
## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

DB Configuration for H2:
=======================
application.properties
------------------------
       
spring.h2.console.enabled=true

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

 spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

About Spring Boot:
==================
Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

/metrics Shows “metrics” information for the current application.

/health Shows application health information.

/info Displays arbitrary application info.

/configprops Displays a collated list of all @ConfigurationProperties.

/mappings Displays a collated list of all @RequestMapping paths.

/beans Displays a complete list of all the Spring Beans in your application.

/env Exposes properties from Spring’s ConfigurableEnvironment.

/trace Displays trace information (by default the last few HTTP requests).



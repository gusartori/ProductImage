# RESTful service using JAX-RS

## Overview 

The application here presented is a simple RESTFul service that performs CRUD operations on top of a relational memory database.
The relational database represent two entities, Product and Image where Product has one to many relationships with Image and one to many relationships with itself.
For this I built a Maven project using Spring Boot framework configured with JAX RS (using Jersey). The application is deployed in an embedded Tomcat and access a H2 in memory database through Hibernate.

## Data and Entities

There is a file to populate the database once the application starts. You can find it here:

[data.sql]()

Entities were created using JPA/Hibernate annotation and are under package **com.project.entities** .

## Tests

As Spring Boot helps a lot with the CRUD operations a natural path is perform Integration Tests instead Unit Tests since mocking would take a large part on it.
I created a class called [ApplicationTests.java]().
They test all operations from both CRUD, Product and Image, and the other relevant methods.
To run automated tests, go to root folder and execute the following command on your terminal:

> mvn test

## Running the application

Make sure you do not have any server running on port 8080, that is where Tomcat will make available our application. To run, go to root folder and type on your terminal:

> mvn spring-boot:run

## Executing REST API commands

You can use applications like **curl** to perform the following calls on your terminal.

### Product CRUD:

> **CREATE:** curl -i -X POST -H "Content-Type:application/json" -d '{"id": "4","name": "New product 4","description": "A test for posting new products!", "parentProduct" : {"id":2,"name":"Glue in a can","description":"Glue can"} }' localhost:8080/product

> **READ:** curl localhost:8080/product/2

> **UPDATE:** curl -i -X PUT -H "Content-Type:application/json" -d '{ "name" : "Product 4", "description": "A test for posting new products!", "parentProduct" : {"id":3,"name":"Negresco crackers","description":"Crackers"}  }' localhost:8080/product/4

> **DELETE:** curl -i -X DELETE -H "Content-Type:application/json" -d '{}' localhost:8080/product/4

### Image CRUD:

> **CREATE:** curl -i -X POST -H "Content-Type:application/json" -d '{"id": "4","type": "colored", "product_id": "2"}' localhost:8080/image

> **READ:** curl localhost:8080/image/4

> **UPDATE:** curl -i -X PUT -H "Content-Type:application/json" -d '{"type": "black&white", "product_id": "3"}' localhost:8080/image/4

> **DELETE:** curl -i -X DELETE -H "Content-Type:application/json" -d '{}' localhost:8080/image/4

### Other requested commands:

>**a) Get all products excluding relationships (child products, images):**
> curl localhost:8080/product/all/norelation

>**b) Get all products including specified relationships (child product and/or images):**
> curl localhost:8080/product/all

>**c) Same as 1 using specific product identity:**
> curl localhost:8080/product/1/norelation

>**d) Same as 2 using specific product identity:**
> curl localhost:8080/product/1

>**e) Get set of child products for specific product:**
> curl localhost:8080/product/1/child

>**f) Get set of images for specific product:**
> curl localhost:8080/product/1/images
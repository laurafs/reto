Solution:
The solution has been implemented with Spring boot. 
* Component cities is a microservice to request info of a city. It has one rest service, that given a city, returns a list of all the connections of that city, and their times of travel. This component has been implemented with spring security, only allowed users can access it. A user and password have been generated. Database used is H2. It allows to have a database that is initialized once the microservice is up. The database is initialized with data contained on a csv file. A few cities and connections have been created. 
* Component routes, is the microservice in charge of calculating the routes. Given an origin and a destiny, t does a request to the cities api, to get all the connections of a city. The algorithm used to calculate the different route is Dijkstra. Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks. In order to construct the graph to calculate the route, several calls are made to the cities component to get all the connections between the cities. 

Project repository:
https://github.com/laurafs/reto.git

To compile using Maven: 
> mvn clean install

To run using spring boot (on each component):
> mvn spring-boot:run

Each of the microservices runs on a different port. It is configured on src/main/resources/application.properties on each of the components. 

How to test the app:
Request: 
http://localhost:8081/routes/City A/City D

Response:
{
    "routesByTime": [
        {
            "id": "City A",
            "name": "City A"
        },
        {
            "id": "City B",
            "name": "City B"
        },
        {
            "id": "City C",
            "name": "City C"
        },
        {
            "id": "City D",
            "name": "City D"
        }
    ],
    "routesByConnections": [
        {
            "id": "City A",
            "name": "City A"
        },
        {
            "id": "City B",
            "name": "City B"
        },
        {
            "id": "City D",
            "name": "City D"
        }
    ]
}

Request: 
http://localhost:8081/routes/City D/City A

Response: 
{
    "routesByTime": [
        {
            "id": "City D",
            "name": "City D"
        },
        {
            "id": "City C",
            "name": "City C"
        },
        {
            "id": "City B",
            "name": "City B"
        },
        {
            "id": "City A",
            "name": "City A"
        }
    ],
    "routesByConnections": [
        {
            "id": "City D",
            "name": "City D"
        },
        {
            "id": "City B",
            "name": "City B"
        },
        {
            "id": "City A",
            "name": "City A"
        }
    ]
}

Libraries used:
* Dozer
Dozer is a Java Bean to Java Bean mapper that recursively copies data from one object to another. Typically, these Java Beans will be of different complex types. 
It's been used on cities microservice to map the cities info between DTO that are exchanged with the client (Request and Response) and the entities (City). It assigns the values from properties of the same name to another bean, doing type conversion automatically. 

* Spring boot
With Spring Boot the tables are turned: no more configuration. Spring Boot assumes that it’s in charge and automatically configures everything for you. So Spring Boot is not used together with a JEE server, but as a replacement.

I've used spring Boot to implement the rest services as it provides a simple way to implement a simple rest service from the beginning. There's no configuration. Spring Boot allows you to quickly build and create Spring applications using the Spring Framework.

* Sl4j
Slf4j is an API designed to give generic access to many logging frameworks and then with a lot of glue code allowing existing code to be used easily. SLF4j is only a logging API/interface. 

* H2 
I've used H2 as database for the Cities microservice. H2 is a relational database management system written in Java. It can be embedded in Java applications or run in the client-server mode. It is easily provided with data. And it can be initialized when the service is launch.

* Docker

* Swagger
Library used for documentation. 

Cities: http://localhost:8080/swagger-ui.html
Routes: http://localhost:8081/swagger-ui.html

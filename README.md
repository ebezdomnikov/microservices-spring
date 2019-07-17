## Microservices project

It contains modules: 
* Configuration service - holds properties for all applications
* Registry service - registers services for further executing
* Product service (PRODUCT API, PERSISTENCE SERVICE) - stores orders in PostgreSQL RDBMS
* Dispatcher service (WRITE REST API) - sends events to queue  
* Routing service - one access point for external client, routes requests to services


To run (all action should be executed from the root of repository):

### 1. Build all modules and docker images for them
    mvn clean package docker:build

### 2. Run infrastructure containers (postgresql, rabbitmq, configuration-service) 
    docker-compose -f docker-compose-infrastructure.yml up -d

### 3. Run business containers  
    docker-compose -f docker-compose-business.yml up -d

# URLs
## Business services

Through routing service:

[Dispatcher service swagger](http://localhost:8082/dispatcher-service/swagger-ui.html)

[Create Product](http://localhost:8082/dispatcher-service/swagger-ui.html#!/dispatcher-endpoint/createProductUsingPOST)

[Update Product](http://localhost:8082/dispatcher-service/swagger-ui.html#!/dispatcher-endpoint/updateProductUsingPOST)

[Product service swagger](http://localhost:8082/product-service/swagger-ui.html)

[List all products](http://localhost:8082/product-service/swagger-ui.html#!/product-endpoint/findAllUsingGET)

[Find one product](http://localhost:8082/product-service/swagger-ui.html#!/product-endpoint/findOneUsingGET)

## Eureka

[Link to UI](http://localhost:8761)
    
## Rabbit MQ

[Link to UI](http://localhost:15672)
 
    login: guest
    password: guest
 
### Wait a little bit, the system need time to up:)

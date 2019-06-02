# ecommerce
A draft of an ecommerce made with springboot for a postgraduate work

#### Prerequisites
- [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3.6+](https://maven.apache.org/install.html)

#### Running

- Before running application is required configure Postgres database on port 5432 and create database 'ecommerce'
- If you prefer run database into [Docker](https://docs.docker.com/install/linux/docker-ee/ubuntu/):
```
docker run --name ecommerce -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=ecommerce postgres:9.6-alpine
```
```
psql -h localhost -U postgres -d postgres
```
```
create database ecommerce
```


After this you can run the application:
```
mvn clean compile spring-boot:run
```

#### Endpoints
##### Customer
```
[GET] /customers
[POST] /customers
[PUT] /customers
[GET] /customers/{id}
[DELETE] /customers/{id}
```

##### Products
```
[GET] /products
[POST] /products
[PUT] /products
[GET] /products/{id}
[DELETE] /products/{id}
```

##### Order
```
[POST] /customers/{customerId}/orders
```

##### StockItems
```
[GET] /stockItems
[POST] /stockItems
[PUT] /stockItems
[GET] /stockItems/{id}
[DELETE] /stockItems/{id}
```

##### StockRequest
```
[POST] /stockRequest
```
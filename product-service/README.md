# Dapr State Management

`product-service` is a microservice to demonstrate:
- Dapr's state management API. The service generates messages to store in a state store.


## Pre-requisites

* Java JDK 17 (or greater):
    * [Microsoft JDK 17](https://docs.microsoft.com/en-us/java/openjdk/download#openjdk-17)
    * [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
    * [OpenJDK 17](https://jdk.java.net/17/)
* [Apache Maven](https://maven.apache.org/install.html) version 3.x.


### Run Java service with Dapr

1. Open a new terminal window and navigate to `product-service` directory:

```bash
cd ./product-service
```

2. Run the Java service app with Dapr: 

```bash
dapr run --app-id product-service --app-port 8081 --resources-path ../dapr-components/ -- java -jar target/product-service-1.0.0-SNAPSHOT.jar
```

```bash
dapr stop --app-id product-service
```

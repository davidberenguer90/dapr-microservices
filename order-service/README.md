# Dapr Subscribe and Secrets Management

`order-service` is a microservice to demonstrate:
- how Dapr enables a publish-subscribe pattern. The service subscribers listen for messages of topic orders.
- Dapr's secrets management API. The service fetches secret from a secret store.


## Pre-requisites

* Java JDK 17 (or greater):
  * [Microsoft JDK 17](https://docs.microsoft.com/en-us/java/openjdk/download#openjdk-17)
  * [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
  * [OpenJDK 17](https://jdk.java.net/17/)
* [Apache Maven](https://maven.apache.org/install.html) version 3.x.


### Run Java service with Dapr

1. Open a new terminal window and navigate to `order-service` directory:

```bash
cd ./order-service
```

2. Run the Java service app with Dapr: 

```bash
dapr run --app-id order-service --app-port 8083 --resources-path ../dapr-components/ -- java -jar target/order-service-1.0.0-SNAPSHOT.jar
```

```bash
dapr stop --app-id order-service
```

# Dapr Input Bindings, Service Invocation and Publish

`checkout-service` is a microservice to demonstrate:
- Dapr's bindings API work with external systems as input. The service listens to input binding events from a system CRON.
- how to use the service invocation API. The service uses Dapr's http proxying capability to invoke a method on other service.
- how Dapr enables a publish-subscribe pattern. The service generates messages and publishes to a specific orders topic.


## Pre-requisites

* Java JDK 17 (or greater):
  * [Microsoft JDK 17](https://docs.microsoft.com/en-us/java/openjdk/download#openjdk-17)
  * [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
  * [OpenJDK 17](https://jdk.java.net/17/)
* [Apache Maven](https://maven.apache.org/install.html) version 3.x.


### Run Java service with Dapr

1. Open a new terminal window and navigate to `checkout-service` directory:

```bash
cd ./checkout-service
```

2. Run the Java service app with Dapr: 

```bash
dapr run --app-id checkout-service --app-port 8082 --resources-path ../dapr-components/ -- java -jar target/checkout-service-1.0.0-SNAPSHOT.jar
```

```bash
dapr stop --app-id checkout-service
```

# Sqills Simple Message Broker  Project

This project simple handle forwarding and also receive message to external systems.

## Running Artemis Messaging Queue

This project needs to bind artemis to receive and send messages

```shell script
docker run -it --rm -p 8161:8161 -p 61616:61616 -e ARTEMIS_USERNAME=quarkus \
-e ARTEMIS_PASSWORD=quarkus vromero/activemq-artemis:latest
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

But first needs to messaging queue system also initiate.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `app-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory. Be aware that it’s
not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/app-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container
using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/app-1.0.0-SNAPSHOT-runner`


## Recommendation
Please use docker-compose on the parent directory to all setup will be auto initiate.

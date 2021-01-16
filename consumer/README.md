# consumer project

This project simple consumer to get message from external binder.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application (INDIVIDUAL)

The application can be packaged using:
```shell script
./mvnw package
```

The application is now runnable using `java -jar target/consumer-1.0.0-SNAPSHOT-runner.jar`. But some properties needs to be provided as system env

```
quarkus.artemis.url=artemis_url
quarkus.artemis.username=artemis_username
quarkus.artemis.password=artemis_pass


topic.consumer=topic
quarkus.http.port=8081

```


## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/consumer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.


## Usage

`./target/consumer-1.0.0-SNAPSHOT-runner` or `java -jar target/consumer-1.0.0-SNAPSHOT-runner.jar`

## Recommendation
Please use docker-compose on the parent directory to all setup will be auto initiate.
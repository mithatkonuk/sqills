# Sqills Message Broker  Project

This project simple handle forwarding and also receive message to external systems.

### Setup

Simple Message Broker Application has small script to handle each of modules process and
containerization to run on everywhere

```shell script
    cd /sqills/setup/
    run ./setup.sh or sh setup  
    
    Started to build broker
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------------< com.sqills:app >---------------------------
[INFO] Building app 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ app ---
[INFO] Deleting /Users/mithat.konuk/sqills_project/sqills/app/target
[INFO]
[INFO] --- quarkus-maven-plugin:1.10.5.Final:generate-code (default) @ app ---
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
....
Current directory : /Users/mithat.konuk/sqills_project/sqills/setup
Started to build consumer
Current directory : /Users/mithat.konuk/sqills_project/sqills/setup
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.sqills:consumer >-------------------------
[INFO] Building consumer 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------

...
sqills_artemis_1 is created
sqills_consumer_1 is created
sqills_app_1 is created
```

setup script is simple runnable script to build all projects and then trigger docker-compose

### Docker Compose

```dockerfile


#
# Simple messaging application
# maintainer mithat konuk <mithatkonuk@gmail.com>
#

version: '3'

services:
  app:
    build:
      context: app
      dockerfile: Dockerfile  # builded from docker file
    ports:
      - "8080:8080"
    restart: on-failure
    environment:
      MESSAGING_URL: tcp://artemis:61616
      MESSAGING_USER: sqills #  use secret , not plain text 
      MESSAGING_PASS: sqills
      MESSAGING_TOPIC: sqillsTopic
      SERVER_PORT: 8080
      JAVA_OPTIONS: -Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager
    depends_on:
      - artemis


  consumer:
    build:
      context: consumer
      dockerfile: Dockerfile # builded from docker file
    ports:
        - "8081:8081"
    restart: on-failure
    environment:
      MESSAGING_URL: tcp://artemis:61616
      MESSAGING_USER: sqills # we can use secrets to handle this user and password
      MESSAGING_PASS: sqills # we can use secret etc
      MESSAGING_TOPIC: sqillsTopic
      MESSAGING_LISTENER_TIMEOUT: 2s # consumer will check every 3  second
      SERVER_PORT: 8081
      JAVA_OPTIONS: -Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager
    depends_on:
        - artemis    

  artemis:
    build:
      context: artemis
      dockerfile: Dockerfile
    ports:
      - "8161:8161"
      - "61616:61616"
      - "5672:5672"
    environment:
      ARTEMIS_USERNAME: sqills
      ARTEMIS_PASSWORD: sqills
```


### Run Application 
```shell
    
  run docker ps 
  
  ![plot](/Users/mithat.konuk/sqills_project/sqills/screenshots/list_of_app.png "Applications")
  
  choose consumer to monitor logs
  
   ![plot](/Users/mithat.konuk/sqills_project/sqills/screenshots/list_of_app.png "Applications")

```
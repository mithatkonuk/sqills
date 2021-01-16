#!/bin/bash

# simple script to build application and create image for containerization
# we can make it more useful, currently just demostration

project_path=../app


# first clean package and build image , lets assume we have alread mvnw which is located in project

package_broker_project() {

	echo "Started to build broker"
	cd $project_path
	./mvnw clean package -Dmaven.test.skip=true # also alternatively we can  use docker-compose build too
	cd -
	echo "Current directory : $(pwd)"
}


package_consumer_project() {

	echo "Started to build consumer"
	echo "Current directory : $(pwd)"

	cd $project_path/../consumer
	./mvnw clean package -Dmaven.test.skip=true # also alternatively we can  use docker-compose build too
	cd -
}


build_container_and_up_in_background() {
	cd $project_path/..
	docker-compose up -d
}


package_broker_project
package_consumer_project
build_container_and_up_in_background




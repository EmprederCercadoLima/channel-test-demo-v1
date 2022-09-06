# API: channel-niubiz-demo-v1

API Channel for demo project

## Table of Contents

  * [Before push commits](#before-push-commits)
  * [Run Docker Container](#run-docker-container)
  * [Explore Rest API](#explore-rest-api)

## Before push commits

1.- Verify that new lines of code have unit tests.

2.- Build and run test, spotbugs, checkstyle.
```bash
mvn clean install
```

3.- Run sonar validation

* Start up local sonarqube service (Go to repository trnq-config).
* Run test for coverage report.

```bash
mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

If you want run test and sonarqube:
```bash
mvn test && mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin
```

If you want run sonarqube only in docker:
```bash
docker pull sonarqube
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube  
```

## Run Docker Container

Running application by docker container.

### Build Application

Ensure you to: 
* Update application properties for the specific profile you want to run (Start docker without docker-compose).

* Build a jar of the application before building a docker image.
* There is only one jar is available in target folder after build. You can build a jar quickly by avoiding validations.

```bash
mvn clean install -Dmaven.test.skip -Dcheckstyle.skip -Dspotbugs.skip
```

### Build Image

```bash
docker build -t channel-test-demo-v1 .
```

You can use some docker command for view image and containers:

| Command                | Description                              |
|------------------------|------------------------------------------| 
|`docker images`         | take a look at the container images.     |
|`docker ps`             | list all the running containers.         |
|`docker ps -a`          | list all the containers.                 |

### Run container

#### Run in docker

Run with specific port, network host to access local resources like database and profile dev
```bash
docker run -p 9001:80 --net=host -d channel-test-demo-v1 dev
```

You can use some docker command to manage the container:

| Command                                     | Description                        |
|---------------------------------------------|------------------------------------| 
|`docker logs [container_id]`                 | view container logs.               |
|`docker exec -it [container_id] /bin/bash`   | connect to container.              |
|`docker stop [container_id]`                 | stop a container                   |
|`docker rm -f [container_id]`                | stop and remove container          |


## Explore Rest API

You can access to API documentation by:

| URL                                             | Description                        |
|-------------------------------------------------|------------------------------------| 
|`http://localhost:9001/v2/api-docs`              | Swagger JSON.                      |
|`http://localhost:9001/swagger-ui/index.html`    | Swagger UI.                        |

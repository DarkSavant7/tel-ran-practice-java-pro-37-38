## Useful links:
### Docker:
https://docs.docker.com/get-docker/

https://learn.microsoft.com/en-us/windows/wsl/install

### Spring:
https://spring.io/quickstart

https://spring.io/guides

https://github.com/spring-guides

https://www.baeldung.com/

https://www.youtube.com/watch?v=BmBr5diz8WA

https://wiremock.org/

https://swagger.io/docs/


### Books:

https://www.manning.com/books/spring-in-action-sixth-edition

https://www.manning.com/books/the-well-grounded-java-developer-second-edition -- overall java

https://www.manning.com/books/java-persistence-with-spring-data-and-hibernate

https://www.manning.com/books/junit-in-action-third-edition -- tests

https://www.manning.com/books/testing-java-microservices -- tests

https://www.manning.com/books/java-persistence-with-hibernate-second-edition

https://www.manning.com/books/spring-boot-in-practice --

https://www.manning.com/books/cloud-native-spring-in-action -- optional about cloud native development using kubernetes

https://www.manning.com/books/camel-in-action-second-edition -- optional, this is integration framework

https://www.manning.com/books/netty-in-action -- optional read if you need to build something asynchronous and custom using java (also spring web flux uses it)

https://www.manning.com/books/spring-microservices-in-action-second-edition -- optional about building microservices using spring

https://www.manning.com/books/quarkus-in-action -- optional quarkus is another one pretty popular java web framework

https://www.manning.com/books/kafka-in-action -- book about kafka

https://www.manning.com/books/learn-kubernetes-in-a-month-of-lunches -- kubernetes

https://www.manning.com/books/securing-cloud-applications -- security

https://www.manning.com/books/kubernetes-native-microservices -- optional

https://www.manning.com/books/kubernetes-secrets-management -- kubernetes

https://www.manning.com/books/docker-in-action-second-edition -- docker

https://www.manning.com/books/kotlin-in-action-second-edition -- kotlin

https://www.manning.com/books/spring-security-in-action -- security

https://www.manning.com/books/gradle-in-action -- gradle


## Telegram @DarkSavant

### Create maven wrapper: in the particular directory (e.g. the project root):
```bash
mvn wrapper:wrapper
```
then you can use your mvnw for *nix or mvnw.cmd for win in order to use maven without installing it
```bash
./mvnw clean install
```
```powershell
mvnw.cmd clean install
```

## DOCKER

### Build: 
```bash
docker build . -t my-app-name
```
### Save to file: 
```bash
docker save -o target/market-app.tar market-app
```

### Run: 
```bash
docker run \
-p 8080:8189/tcp \
-p 8080:8189/udp \
--name market-app-test \
--add-host=host.docker.internal:host-gateway \
-e JAVA_TOOL_OPTIONS=" \
    -Dspring.datasource.url=jdbc:mysql://host.docker.internal:3306/market \
    -Dlogging.level.de.telran.marketapp=DEBUG" \
-d market-app:latest
```
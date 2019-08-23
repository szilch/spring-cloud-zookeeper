#Example of using Zookeeper in Spring Cloud
##Short Intro
This application demonstrates the usage of [Apache Zookeeper](https://zookeeper.apache.org/) 
in a [Spring Cloud](https://spring.io/projects/spring-cloud) Environment.

**Apache Zookeeper is used as:**
* Service Registry
* Distributed Application Configuration (not implemented yet)

**Spring Cloud is used for:**
* Registering services in Zookeeper
* Getting a load-balanced Http-Client with  [OpenFeign](https://spring.io/projects/spring-cloud-openfeign) and Ribbon
##Usage
1. Create the Zookeeper Environment with Docker in folder docker-zookeeper: \
`docker-compose up -d`
2. Open your browser to check if Zookeeper is running: http://localhost:8000
3. Compile everything...
4. Start different weather-data-producers: \
`java -jar weather-data-producer-1.0.0-SNAPSHOT.jar --server.port=8080 --app.location=Mannheim`
`java -jar weather-data-producer-1.0.0-SNAPSHOT.jar --server.port=8081 --app.location=Stuttgart`
5. Start a weather-data-client: \
`java -jar weather-data-client-1.0.0-SNAPSHOT.jar`
6. If everything works fine, you should see some random weather-data alternating from the providers Mannheim and Stuttgart :)
7. Further, check http://locahost:8000 -> You should see a new node 'services' with  different instances of the weather-data-provider
8. Have fun... stop one weather-data-service or create a third one... Check the output of the weather-data-client


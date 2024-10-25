FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/springBoot-0.0.1-SNAPSHOT.jarjar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
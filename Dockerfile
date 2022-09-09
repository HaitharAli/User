FROM openjdk:18-alpine
EXPOSE 8080
ADD target/user-system.jar user-system.jar
ENTRYPOINT ["java","-jar","/user-system.jar"]
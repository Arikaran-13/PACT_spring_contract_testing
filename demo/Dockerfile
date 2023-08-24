FROM openjdk:19
WORKDIR /app
ADD target/demo-producer.jar producer.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","producer.jar"]
FROM openjdk:17-slim
WORKDIR /app
COPY target/topping-service-0.0.1-SNAPSHOT.jar /app/topping-service.jar
EXPOSE 7070
EXPOSE 8080
CMD ["java", "-jar", "/app/topping-service.jar"]


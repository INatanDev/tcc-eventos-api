FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/eventos-api-1.0.1-snapshot.jar
CMD ["java", "-jar", "/app/eventos-api-1.0.1-snapshot.jar"]
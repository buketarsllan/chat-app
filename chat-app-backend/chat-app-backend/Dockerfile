FROM openjdk:17-jdk-slim

WORKDIR /chat-app

ARG JAR_FILE=target/chat-app-backend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /chat-app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/chat-app/app.jar"]

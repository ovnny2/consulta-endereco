FROM alpine:3.14
RUN apk add --no-cache openjdk11
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080
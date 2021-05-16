FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/proyecto-backend-spring-boot.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

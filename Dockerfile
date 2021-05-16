FROM openjdk:11
COPY ./target/proyecto-backend-spring-boot.jar proyecto-backend-spring-boot.jar
CMD ["java", "-jar", "proyecto-backend-spring-boot.jar"]

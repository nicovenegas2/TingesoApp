FROM openjdk:17
ARG JAR_FILE=target/TingesoApp.jar
COPY ${JAR_FILE} TingesoApp.jar
ENTRYPOINT ["java", "-jar", "/TingesoApp.jar"]
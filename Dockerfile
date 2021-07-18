FROM openjdk:12-jdk-alpine
RUN mkdir /dev/services
COPY src/main/java/* /dev/services

COPY target/Distanziamento-0.0.1-SNAPSHOT.jar Distanziamento-0.0.1-SNAPSHOT.jar
VOLUME /dev/services
EXPOSE 8080
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Distanziamento-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["java","-jar","/Distanziamento-0.0.1-SNAPSHOT.jar"]
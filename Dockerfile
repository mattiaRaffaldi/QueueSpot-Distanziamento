FROM openjdk:12-jdk-alpine
RUN mkdir /dev/services
RUN
COPY src/main/java/* /dev/services

COPY target/Distanziamento-1.0.jar Distanziamento-1.0.jar
VOLUME /dev/services
EXPOSE 8080
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Distanziamento-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["java","-jar","/Distanziamento-1.0.jar"]
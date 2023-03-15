FROM amazoncorretto:17-alpine-jdk
MAINTAINER fd
COPY target/fdp-0.0.1-SNAPSHOT.jar  fdpBack.jar
ENTRYPOINT ["java","-jar","/fdpBack.jar"]

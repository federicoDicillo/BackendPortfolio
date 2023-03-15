FROM amazoncorretto:8
MAINTAINER fd
COPY target/fdp-0.0.1-SNAPSHOT.jar fdpBack.jar
ENTRYPOINT ["java","-jar","/fdpBack.jar"]

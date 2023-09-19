# Use Red Hat's Universal Base Image 8 with Java 11
FROM registry.access.redhat.com/ubi8/openjdk-17:1.17-1.1693366272 as base

COPY target/camelsb-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","/app.jar"]


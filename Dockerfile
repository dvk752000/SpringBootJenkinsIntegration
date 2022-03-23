FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM jenkins/jenkins:lts
#USER root
#RUN apt-get update
#RUN curl -sSL https://get.docker.com/ | sh

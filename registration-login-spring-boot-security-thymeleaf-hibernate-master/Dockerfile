FROM openjdk:11

## Name of Jar file.
ARG JAR_NAME=registration-login-spring-boot-security-thymeleaf-0.0.1-SNAPSHOT.jar
ENV JAR_NAME=$JAR_NAME

## Install admin module
ADD target/$JAR_NAME .

## Permissions to Jar file.
RUN chmod 755 $JAR_NAME

## Create User
RUN useradd -ms /bin/bash appuser
USER appuser

EXPOSE 8083
## Run client module
CMD java $PROGRAM_OPTIONS -jar $JAR_NAME $PROGRAM_ARGUMENTS
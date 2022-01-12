FROM openjdk:11

## Name of Jar file.
ARG JAR_NAME=book-0.0.1-SNAPSHOT.jar
ENV JAR_NAME=$JAR_NAME

## Install admin module
ADD book/target/$JAR_NAME .

## Permissions to Jar file.
RUN chmod 755 $JAR_NAME

## Create User
RUN useradd -ms /bin/bash appuser
USER appuser

EXPOSE 8087
## Run client module
CMD java $PROGRAM_OPTIONS -jar $JAR_NAME $PROGRAM_ARGUMENTS
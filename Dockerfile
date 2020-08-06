FROM openjdk:8-jdk-alpine

LABEL maintainer=alon.shoshani@gmail.com

RUN \
    apk -U upgrade -a && apk update && apk add --allow-untrusted --no-cache bash

# initial system requirements
RUN \
    # update system and install core tools and goodies
    apk add git vim curl

RUN \
      adduser -D -s /bin/bash alon && \
      chown -R alon:alon /var/log /opt

ENV \
    TERM=xterm-256color

ADD target/bluerbn-1.0-SNAPSHOT.jar /opt/

WORKDIR /opt

ENTRYPOINT java -Denvironment=prod\
    -Dcom.sun.management.jmxremote.authenticate=false\
    -Dcom.sun.management.jmxremote.ssl=false\
    -jar bluerbn-1.0-SNAPSHOT.jar

EXPOSE 8080

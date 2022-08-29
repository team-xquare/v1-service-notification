FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080

COPY ./notification-infrastructure/build/libs/*.jar app.jar

ARG CLOUD_CONFIG_USERNAME
ARG CLOUD_CONFIG_PASSWORD
ARG PROFILE

ENV CLOUD_CONFIG_USERNAME ${CLOUD_CONFIG_USERNAME}
ENV CLOUD_CONFIG_PASSWORD ${CLOUD_CONFIG_PASSWORD}
ENV PROFILE ${PROFILE}

ENTRYPOINT ["java","-jar","/app.jar"]

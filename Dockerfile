FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080

COPY ./notification-infrastructure/build/libs/*.jar app.jar

ARG CLOUD_CONFIG_USERNAME
ARG CLOUD_CONFIG_PASSWORD
ARG CLOUD_CONFIG_IMPORT_URL
ARG PROFILE

ENV CLOUD_CONFIG_USERNAME ${CLOUD_CONFIG_USERNAME}
ENV CLOUD_CONFIG_PASSWORD ${CLOUD_CONFIG_PASSWORD}
ENV CLOUD_CONFIG_IMPORT_URL ${CLOUD_CONFIG_IMPORT_URL}
ENV PROFILE ${PROFILE}

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/app.jar"]

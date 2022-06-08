FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080

COPY ./notification-infrastructure/build/libs/*.jar app.jar

ARG DB_USERNAME
ARG DB_PASSWORD
ARG DB_URL
ARG DB_NAME

ARG FCM_VALUE

ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY

ENV DB_USERNAME ${DB_USERNAME}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_URL ${DB_URL}
ENV DB_NAME ${DB_NAME}

ENV FCM_VALUE ${FCM_VALUE}

ENV AWS_ACCESS_KEY ${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY ${AWS_SECRET_KEY}

ENTRYPOINT ["java","-jar","/app.jar"]

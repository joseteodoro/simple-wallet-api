FROM openjdk:11-jre

RUN mkdir /app
WORKDIR /app
COPY ./target/wallet-0.0.1-SNAPSHOT.jar application.jar

CMD [ "java", "-jar", "/app/application.jar" ]
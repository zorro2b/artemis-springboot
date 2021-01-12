FROM adoptopenjdk/openjdk11:jdk-11.0.9_11-alpine

RUN mkdir /app
WORKDIR /app
COPY ./target/amqdemo.jar /app

CMD java -jar ./amqdemo.jar
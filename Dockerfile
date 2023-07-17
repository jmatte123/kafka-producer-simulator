FROM  gradle:5.3.0-jdk-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew clean build

FROM openjdk:17-jre-slim
MAINTAINER Joe Matteson
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/KarkaProducerSimulator.jar
ENTRYPOINT ["java", "-jar", "/app/KarkaProducerSimulator.jar"]
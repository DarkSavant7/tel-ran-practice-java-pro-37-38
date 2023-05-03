#FROM bellsoft/liberica-openjdk-debian:11.0.12-7 as builder
#FROM bellsoft/liberica-openjdk-alpine:11.0.12-7 as builder
FROM maven:3.8.4-eclipse-temurin-17 as builder
WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests


#FROM bellsoft/liberica-openjdk-debian:11.0.12-7 as production
#FROM bellsoft/liberica-openjdk-alpine:11.0.12-7 as production
#FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine as production
FROM eclipse-temurin:17-jre-alpine as production
VOLUME /tmp
WORKDIR /app
#
COPY --from=builder /app/target/*docker  /app/
RUN mkdir /app/log
RUN mkdir /app/config
ENV JAVA_TOOL_OPTIONS=""

ENTRYPOINT ["sh", "-c", "java ${JAVA_TOOL_OPTIONS} -jar market-app-0.0.1-SNAPSHOT.jar"]
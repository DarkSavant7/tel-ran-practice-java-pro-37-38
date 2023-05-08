FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app

COPY market-app/pom.xml .
COPY market-app/src src
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:17-jre-alpine as production
VOLUME /tmp
WORKDIR /app
#
COPY --from=builder /app/target/market-app.jar  /app/
RUN mkdir /app/log
RUN mkdir /app/config
ENV JAVA_TOOL_OPTIONS=""

ENTRYPOINT ["sh", "-c", "java ${JAVA_TOOL_OPTIONS} -jar market-app.jar"]
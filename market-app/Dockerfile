FROM eclipse-temurin:17-jre-alpine as production
VOLUME /tmp
WORKDIR /app
#
COPY target/market-app.jar  /app/
RUN mkdir /app/log
RUN mkdir /app/config
ENV JAVA_TOOL_OPTIONS=""

ENTRYPOINT ["sh", "-c", "java ${JAVA_TOOL_OPTIONS} -jar market-app.jar"]
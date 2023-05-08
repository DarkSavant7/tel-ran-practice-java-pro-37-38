FROM eclipse-temurin:17-jre-alpine as production
VOLUME /tmp
WORKDIR /app
#
COPY market-app/target/market-app.jar  /app/
ENV JAVA_TOOL_OPTIONS=""

ENTRYPOINT ["sh", "-c", "java ${JAVA_TOOL_OPTIONS} -jar market-app.jar"]
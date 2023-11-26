FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/api-gateway-2.0.jar /app/api-gateway-2.0.jar

ENTRYPOINT ["java", "-jar", "api-gateway-2.0.jar"]
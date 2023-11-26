FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/config-2.0.jar /app/config-2.0.jar

ENTRYPOINT ["java", "-jar", "config-2.0.jar"]
FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/report-2.0.jar /app/report-2.0.jar

ENTRYPOINT ["java", "-jar", "report-2.0.jar"]
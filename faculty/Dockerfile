FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/faculty-2.0.jar /app/faculty-2.0.jar

ENTRYPOINT ["java", "-jar", "faculty-2.0.jar"]
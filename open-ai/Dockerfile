FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/open-ai-2.0.jar /app/open-ai-2.0.jar

ENTRYPOINT ["java", "-jar", "open-ai-2.0.jar"]

FROM eclipse-temurin:21

LABEL mentainer="vukmanovic.milos@gmail.com"

WORKDIR /app

COPY target/eureka-2.0.jar /app/eureka-2.0.jar

ENTRYPOINT ["java", "-jar", "eureka-2.0.jar"]
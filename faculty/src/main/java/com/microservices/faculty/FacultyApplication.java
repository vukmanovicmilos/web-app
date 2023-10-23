package com.microservices.faculty;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info =
@Info(title = "Microservices",
        description = "Demo microservice application",
        contact = @Contact(name = "Miloš Vukmanović", url = "https://www.linkedin.com/in/vukmanovicmilos/"),
        version = "v2.0"))
@SpringBootApplication
public class FacultyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacultyApplication.class, args);
    }
}

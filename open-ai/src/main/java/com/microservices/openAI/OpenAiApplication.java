package com.microservices.openAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OpenAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiApplication.class, args);
	}

}

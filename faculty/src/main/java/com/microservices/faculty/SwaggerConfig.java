package com.microservices.faculty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
//	    registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//	    registry.addRedirectViewController("/documentation/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//	    registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
		registry.addRedirectViewController("/", "/swagger-ui.html");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
	}

	@Bean
	public Docket apiDocket() {

		Contact contact = new Contact("Miloš Vukmanović", "https://github.com/vukmanovicmilos",
				"vukmanovic.milos@gmail.com");

		List<VendorExtension> vendorExtensions = new ArrayList<>();

		ApiInfo apiInfo = new ApiInfo("Faculty documentation", "This pages documents Faculty endpoints", "1.0",
				"https://github.com/vukmanovicmilos/microservices", contact, "", "", vendorExtensions);

		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select()
				.apis(RequestHandlerSelectors.basePackage("com.microservices.faculty")).paths(PathSelectors.any()).build();

		return docket;

	}

}

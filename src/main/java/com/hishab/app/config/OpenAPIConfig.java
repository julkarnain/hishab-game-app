package com.hishab.app.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Value("${hishab.openapi.dev-url}")
	private String devUrl;

	@Value("${hishab.openapi.prod-url}")
	private String prodUrl;

	@Bean
	OpenAPI openAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");

		return new OpenAPI().info(apiInfo()).servers(List.of(devServer, prodServer));
	}

    @Bean
    GroupedOpenApi gamesOpenApi() {
	   String paths[] = {"/api/1.0/game/**"};
	   return GroupedOpenApi.builder().group("games").pathsToMatch(paths)
	         .build();
	}
   
    @Bean
    GroupedOpenApi playersOpenApi() {
	   String paths[] = {"/api/1.0/player/**"};
	   return GroupedOpenApi.builder().group("players").pathsToMatch(paths)
	         .build();
	}

    @Bean
    GroupedOpenApi allOpenApi() {
       String packagesToscan[] = {"com.hishab.app.controller"};
       return GroupedOpenApi.builder().group("all-apis").packagesToScan(packagesToscan)
             .build();
    }
    
	private Info apiInfo() {

		Contact contact = new Contact();
		contact.setEmail("julkar.elixirsoft.nain@gmail.com");
		contact.setName("Julkar Nain");
		contact.setUrl("https://www.linkedin.com/in/julkarnain/");

		License mitLicense = new License()
				.name("MIT License")
				.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info()
				.title("Hishab-Game-App")
				.version("1.0")
				.contact(contact)
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("Terms of Service")
				.license(mitLicense);

		return info;

	}
}

package com.gr.contacorrente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(info())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gr.contacorrente.controller"))
				.build();
	}
	
	private ApiInfo info() {
		return new ApiInfoBuilder().title("API - Controle de Conta Corrente")
				.description("Projeto simples de conta corrente para PF e PJ, com Hibernate, H2 e Swagger")
				.contact(new Contact("Gustavo Grossmann", "https://github.com/gustavogrossmann89/", "gustavo.grossmann@gmail.com"))
				.version("1.0")
				.build();
	}
}

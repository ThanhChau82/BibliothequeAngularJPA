package fr.projetAngularJPA.bibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BibliothequeAngularJpaApplication {
	private static final String DOCUMENT_BASE_PACKAGE = "fr.projetAngularJPA.bibliotheque";
	private static final String DOCUMENT_TITLE = "Library Spring Boot REST API Documentation";
	private static final String DOCUMENT_DESCRIPTION = "REST APIs for managing books loans in a library";
	private static final String DOCUMENT_AUTHOR_NAME = "Thanh Châu";
	private static final String DOCUMENT_AUTHOR_WEBSITE = "https://thanhchâu.developpez.com";
	private static final String DOCUMENT_AUTHOR_MAIL = "noreply.library.test@gmail.com";
	private static final String DOCUMENT_VERSION = "1.0";

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeAngularJpaApplication.class, args);
	}

	/**
	 * Build the datas of graphic render of API's JSON document.
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(DOCUMENT_BASE_PACKAGE)).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	/**
	 * Describre API's informations : title, description, contact, version.
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(DOCUMENT_TITLE).description(DOCUMENT_DESCRIPTION)
				.contact(new Contact(DOCUMENT_AUTHOR_NAME, DOCUMENT_AUTHOR_WEBSITE, DOCUMENT_AUTHOR_MAIL))
				.version(DOCUMENT_VERSION).build();
	}

}

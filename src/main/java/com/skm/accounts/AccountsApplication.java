package com.skm.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(
		info=@Info(
				title = "Account ms REST api docs",
				description ="Account ms documentation",
				contact = @Contact(
						name = "Sofia Medina",
						email="sofi@email.com",
						url="ssomeurl.com"
						),
						license=@License(
								name = "Apache 2.0",
								url = "someurl.com"

				        )

                ),
		externalDocs = @ExternalDocumentation(
				description =  "some desc ext",
				url = "https://www.example.com/"
		)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

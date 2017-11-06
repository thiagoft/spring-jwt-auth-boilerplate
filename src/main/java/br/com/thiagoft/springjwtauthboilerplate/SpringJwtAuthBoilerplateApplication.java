package br.com.thiagoft.springjwtauthboilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringJwtAuthBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtAuthBoilerplateApplication.class, args);
	}
}

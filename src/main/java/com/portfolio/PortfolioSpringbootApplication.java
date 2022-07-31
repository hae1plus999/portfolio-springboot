package com.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortfolioSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioSpringbootApplication.class, args);
	}

}

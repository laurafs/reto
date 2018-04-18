package com.adidas.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoutesApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(RoutesApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Running...");
		SpringApplication.run(RoutesApplication.class, args);
	}
}

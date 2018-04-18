package com.adidas.cities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitiesApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(CitiesApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Running...");
		SpringApplication.run(CitiesApplication.class, args);
	}
}

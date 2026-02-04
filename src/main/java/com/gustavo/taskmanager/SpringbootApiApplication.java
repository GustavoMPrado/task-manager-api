package com.gustavo.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringbootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}
}


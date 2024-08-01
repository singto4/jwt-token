package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AppTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTokenApplication.class, args);
	}

}

package com.convention;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConventionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConventionApplication.class, args);
	}
}

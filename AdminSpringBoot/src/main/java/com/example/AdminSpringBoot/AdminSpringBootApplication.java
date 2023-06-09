package com.example.AdminSpringBoot;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminSpringBootApplication.class, args);
	}

}
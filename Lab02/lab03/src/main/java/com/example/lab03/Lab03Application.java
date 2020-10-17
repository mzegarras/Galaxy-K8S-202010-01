package com.example.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Lab03Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab03Application.class, args);
	}

}

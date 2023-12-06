package com.tads4.tads4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Tads4Application {
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(Tads4Application.class, args);
	}

}

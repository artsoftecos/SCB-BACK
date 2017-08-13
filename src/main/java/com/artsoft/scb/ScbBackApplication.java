package com.artsoft.scb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ScbBackApplication {

	@GetMapping("/")
	public String hola(){
		return "Hola mundo!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ScbBackApplication.class, args);
	}
}

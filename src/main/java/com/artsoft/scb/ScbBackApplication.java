package com.artsoft.scb;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScbBackApplication{
	
	@GetMapping("/")
	public String hola(){
		return "Artsoft rules!";
	}
	
	
	public static void main(String[] args) {
		 // can be set runtime before Spring instantiates any beans
        // TimeZone.setDefault(TimeZone.getTimeZone("GMT+00:00"));
        //TimeZone.setDefault(TimeZone.getTimeZone("GMT-5:00"));
		SpringApplication.run(ScbBackApplication.class, args);
	}
}

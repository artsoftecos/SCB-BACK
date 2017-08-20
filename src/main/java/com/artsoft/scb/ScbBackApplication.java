package com.artsoft.scb;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.DocumentServiceConfig;
import com.artsoft.scb.model.bll.IDocumentService;

@SpringBootApplication
@RestController
public class ScbBackApplication{
	
	@GetMapping("/")
	public String hola(){
		return "Hola mundo!";
	}
	
	
	public static void main(String[] args) {		
		ApplicationContext context = new AnnotationConfigApplicationContext(DocumentServiceConfig.class);
        //IDocumentService documentService = (IDocumentService) context.getBean("greetingCollector");
        		
		SpringApplication.run(ScbBackApplication.class, args);
	}
}

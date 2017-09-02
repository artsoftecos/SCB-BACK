package com.artsoft.scb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.OferentService;
import com.artsoft.scb.model.entity.Oferent;

@RestController
@RequestMapping(path = "/oferent")
public class OferentController {
	
	@Autowired
	private OferentService oferentService;
	
	@PostMapping()
	public ResponseEntity<?> createOferent(@RequestBody Oferent oferent) {
		oferent.setState("PENDIENTE");
		try {
			oferentService.createOferent(oferent);			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Oferente creado");
	}
	
	@PostMapping("/approve/{nit}")
	public ResponseEntity<?> approveOferent(@PathVariable("nit") String nit){
		oferentService.acceptOferent(nit);
		return ResponseEntity.status(HttpStatus.OK).body("Oferente aceptado");
	}
	
	@PostMapping("/reject/{nit}")
	public ResponseEntity<?> denyOferent(@PathVariable("nit") String nit){
		oferentService.rejectOferent(nit);
		return ResponseEntity.status(HttpStatus.OK).body("Oferente rechazado");
	}
}


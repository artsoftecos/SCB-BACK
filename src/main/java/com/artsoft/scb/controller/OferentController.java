package com.artsoft.scb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		try {
			oferentService.createOferent(oferent);			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Oferente creado");
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllOferents() {
		List<Oferent> oferents = new ArrayList<Oferent>();
		try {
			oferents = oferentService.getAllOferents();			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(oferents);
	}
}


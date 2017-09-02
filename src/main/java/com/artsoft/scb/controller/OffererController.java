package com.artsoft.scb.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.OffererService;
import com.artsoft.scb.model.entity.Offerer;

@RestController
@RequestMapping(path = "/offerer")
public class OffererController {
	
	@Autowired
	private OffererService offererService;
	
	@PostMapping()
	public ResponseEntity<?> createOferent(@RequestBody Offerer oferent) {
		JSONObject response = new JSONObject();
		try {
			offererService.createOferent(oferent);	
			response.put("Response", "Oferente creado.");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllOferents() {
		List<Offerer> oferents = new ArrayList<Offerer>();
		try {
			oferents = offererService.getAllOferents();			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(oferents);
	}
}


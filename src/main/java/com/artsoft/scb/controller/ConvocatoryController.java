package com.artsoft.scb.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.ConvocatoryService;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;

@RestController
@PreAuthorize("hasRole('ROLE_OFFERER')")
@RequestMapping(path = "/convocatory")
public class ConvocatoryController {
	
	/**
	 * crea la convocatoria
	 */
	
	@Autowired
	private ConvocatoryService convocatoryService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> post(@RequestBody Convocatory convocatory) {
		JSONObject response = new JSONObject();
		try {			
			convocatoryService.createConvocatory(convocatory);
			response.put("Response", "Convocatoria Creada");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getByOfferer")
	public ResponseEntity<?> post(@RequestBody String mailOfferer) {
		JSONObject response = new JSONObject();
		try {
			response.put("Response", convocatoryService.getByOffer(mailOfferer));
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping(path = "/getById")
	public ResponseEntity<?> post(@RequestBody int id) {
		JSONObject response = new JSONObject();
		try {
			response.put("Response", convocatoryService.getById(id));
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getByState")
	public ResponseEntity<?> post(@RequestBody ConvocatoryState convState) {
		JSONObject response = new JSONObject();
		try {
			response.put("Response", convocatoryService.getByState(convState));
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getByPendingPhases")
	public ResponseEntity<?> get() {
		JSONObject response = new JSONObject();
		try {
			response.put("Response", convocatoryService.getByPendingPhases());
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
}


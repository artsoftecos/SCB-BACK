package com.artsoft.scb.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.ConvocatoryService;
import com.artsoft.scb.model.entity.Convocatory;

@RestController
@PreAuthorize("hasRole('ROLE_OFFERER')")
@RequestMapping(path = "/convocatory")
public class ConvocatoryController {
	
	/**
	 * crea la convocatoria
	 */
	
	@Autowired
	private ConvocatoryService convocatoryService;
	
	@PostMapping()
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
	
}


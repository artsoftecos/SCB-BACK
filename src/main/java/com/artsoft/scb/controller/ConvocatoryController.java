package com.artsoft.scb.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_OFFERER')")
@RequestMapping(path = "/convocatory")
public class ConvocatoryController {
	
	/**
	 * crea la convocatoria
	 */
	@PostMapping()
	public ResponseEntity<?> post(@RequestBody String convocatory) {	
		//Este es el obj q envio: "name":"xfaaewd","place":"fds","description":"dsgdsf","type":{"id":"1"}
		JSONObject response = new JSONObject();
		try {			
			//creara la convocatoria
			response.put("Response", "Convocatoria Creada");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
}


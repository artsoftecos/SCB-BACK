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

import com.artsoft.scb.model.bll.PhaseService;
import com.artsoft.scb.model.entity.Phase;

@RestController
@PreAuthorize("hasRole('ROLE_OFFERER')")
@RequestMapping(path = "/phase")
public class PhaseController {

	@Autowired
	private PhaseService phaseService;
	/**
	 * crea la fase
	 */
	@PostMapping()
	public ResponseEntity<?> post(@RequestBody Phase phase) {	
		//TODO: Este es el obj q envio: 
		/*
		  name: string;
    description: string;
    state: StatePhase;
    startDate: string;
    finishDate: string;
    startApprovalPostulation: string;
    resultDate: string;
		 */
		JSONObject response = new JSONObject();
		try {
			System.out.println("En el controlador: " + phase.getStartDate());
			phaseService.createPhase(phase);
			response.put("Response", "Fase Creada");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
}

package com.artsoft.scb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.ApplicantService;
import com.artsoft.scb.model.entity.Applicant;

@RestController
@RequestMapping(path = "/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
		
	@PostMapping()
	public ResponseEntity<?> createApplicant(@RequestBody Applicant applicant) {
		try {
			applicantService.createApplicant(applicant);			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Solicitante creado");
	}
}

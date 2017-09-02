package com.artsoft.scb.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		 JSONObject response = new JSONObject();
		try {
			applicantService.createApplicant(applicant);	
			response.put("Response", "Bienvenido, le llegará un correo para completar su registro.");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());		
	}
	
	@GetMapping("/welcome/{token}")
	public ResponseEntity<?> approveApplicant(@PathVariable("token")String token) {
		JSONObject response = new JSONObject();
		try {
			applicantService.approveRegisterApplicant(token);
			response.put("Response", "Solicitante aprobado.");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping()
	public ResponseEntity<?> getApplicant() {
		String h = "";
		return ResponseEntity.status(HttpStatus.OK).body(h.toString());
	}
}

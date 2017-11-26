package com.artsoft.scb.controller;

import java.util.List;

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

import com.artsoft.scb.model.bll.ApplicantPerPhaseService;
import com.artsoft.scb.model.entity.ApplicantPerPhase;

@RestController
@RequestMapping(path = "/AppPerPhase")
public class ApplicantPerPhaseController {
	
	@Autowired
	private ApplicantPerPhaseService applicantPerPhaseService;

	@GetMapping(path = "/applicantsPerPhase/{id}")
	public ResponseEntity<?> getApplicantsPerPhase(@PathVariable("id") int id){
		List<ApplicantPerPhase> applicantsPerPhase = applicantPerPhaseService.getApplicantsPerPhase(id);
		return ResponseEntity.status(HttpStatus.OK).body(applicantsPerPhase);
		
	}
	
	@GetMapping(path = "/byId/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		List<ApplicantPerPhase> applicantsPerPhase = applicantPerPhaseService.getApplicantPerPhaseByState(id);
		return ResponseEntity.status(HttpStatus.OK).body(applicantsPerPhase);
		
	}
	
	@PostMapping(path = "/asociate")
	public ResponseEntity<?> asociate(@RequestBody ApplicantPerPhase applicantPerPhase){
		JSONObject response = new JSONObject();
		try {
			applicantPerPhaseService.asociateApplicantToAPhase(applicantPerPhase);
			response.put("Response", "Solicitante asociado a la fase exitosamente");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getAsociation/{id}")
	public ResponseEntity<?> getByAsociationId(@PathVariable("id") int idAsociation){
		ApplicantPerPhase applicantPerPhase = null;
		try {
			applicantPerPhase = applicantPerPhaseService.getApplicantAsociatedToAPhase(idAsociation);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(applicantPerPhase);
	}

}

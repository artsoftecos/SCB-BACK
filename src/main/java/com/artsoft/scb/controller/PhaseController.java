package com.artsoft.scb.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.PhaseService;
import com.artsoft.scb.model.entity.Phase;
import com.artsoft.scb.model.entity.StatePhaseAndAplicant;

@RestController
@PreAuthorize("hasAnyRole('ROLE_OFFERER','ROLE_APPLICANT')")
@RequestMapping(path = "/phase")
public class PhaseController {

	@Autowired
	private PhaseService phaseService;
	/**
	 * crea la fase
	 */
	@PostMapping()
	public ResponseEntity<?> post(@RequestBody Phase phase) {	
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
	
	@PostMapping(path = "/edit")
	public ResponseEntity<?> editPhase(@RequestBody Phase phase){
		JSONObject response = new JSONObject();
		System.out.println("entra: " + phase.getStartDate());
		try {
			phaseService.editPhase(phase);
			response.put("Response", "Fase editada con �xito");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletePhase(@PathVariable("id") int idPhase){
		JSONObject response = new JSONObject();
		try {
			phaseService.deletePhase(idPhase);
			response.put("Response", "Fase eliminada con éxito");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getPhasesOfConvocatory/{idConvocatory}")
	public ResponseEntity<?> getPhasesByConvocatoryId(@PathVariable("idConvocatory") int idConvocatory){
		List<Phase> phases = new ArrayList<Phase>();
		try {
			phases = phaseService.getPhaseByConvocatoryId(idConvocatory);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(phases);
	}
	
	@GetMapping(path = "/getPhases/{idPhase}")
	public ResponseEntity<?> getPhasesByPhaseId(@PathVariable("idPhase") int idPhase){
		Phase phase;
		try {
			phase = phaseService.getPhaseById(idPhase);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(phase);
	}
	
	@GetMapping(path = "/getCurrentPhase/{idConvocatory}/{mailApplicant:.+}")
	public ResponseEntity<?> getCurrentPhase(@PathVariable("idConvocatory") int idConvocatory, @PathVariable("mailApplicant") String mailApplicant){
		StatePhaseAndAplicant statePhaseAndAplicant;
		try {
			statePhaseAndAplicant = phaseService.getCurrentPhaseForConvocatoryAndApplicant(idConvocatory, mailApplicant);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(statePhaseAndAplicant);
	}
	
	
	@GetMapping(path = "/getCurrentPhaseByConvocatory/{idConvocatory}")
	public ResponseEntity<?> getCurrentPhase(@PathVariable("idConvocatory") int idConvocatory){
		StatePhaseAndAplicant statePhaseAndAplicant;
		try {
			statePhaseAndAplicant = phaseService.getCurrentPhaseForConvocatory(idConvocatory);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(statePhaseAndAplicant);
	}
	
}


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

import com.artsoft.scb.model.bll.ApplicantPerPhaseService;
import com.artsoft.scb.model.bll.OffererService;
import com.artsoft.scb.model.bll.PhaseService;
import com.artsoft.scb.model.entity.Offerer;

@RestController
@RequestMapping(path = "/offerer")
public class OffererController {
	
	@Autowired
	private OffererService offererService;
	
	@Autowired
	private ApplicantPerPhaseService applicantPerPhaseService;
	
	@PostMapping
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
	

	@PostMapping("/approve/{nit}")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> approveOferent(@PathVariable("nit") String nit){
		JSONObject response = new JSONObject();
		
		try {
			offererService.acceptOferent(nit);
			response.put("Response", "Oferente aceptado.");
		}			
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());	
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping("/reject/{nit}")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> denyOferent(@PathVariable("nit") String nit){
		JSONObject response = new JSONObject();	
		
		try {
			offererService.rejectOferent(nit);
			response.put("Response", "Oferente rechazado.");
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());	
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}

	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
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
	
	@GetMapping("/rejected")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> getRejectedOferents() {
		List<Offerer> oferents = new ArrayList<Offerer>();
		try {
			oferents = offererService.getRejectedOferents();			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(oferents);

	}
	
	@GetMapping("/approved")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> getApprovedOferents() {
		List<Offerer> oferents = new ArrayList<Offerer>();
		try {
			oferents = offererService.getApprovedOferents();			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(oferents);

	}
	
	@GetMapping("/pending")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> getPendingOferents() {
		List<Offerer> oferents = new ArrayList<Offerer>();
		try {
			oferents = offererService.getPendingOferents();			
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(oferents);

	}
	
	@PostMapping("/approvePhase/{id}")
	public ResponseEntity<?> approveApplicantInAPhase(@PathVariable int id){
		JSONObject response = new JSONObject();
		
		try {
			applicantPerPhaseService.acceptAplicantFromAPhase(id);
			response.put("Response", "Solicitante aprobado");
		}			
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());	
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping("/rejectPhase/{id}")
	public ResponseEntity<?> rejectApplicantInAPhase(@PathVariable int id){
		JSONObject response = new JSONObject();
		
		try {
			applicantPerPhaseService.rejectAplicantFromAPhase(id);
			response.put("Response", "Solicitante rechazado");
		}			
		catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());	
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
}


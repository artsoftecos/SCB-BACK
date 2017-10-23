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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.ConvocatoryService;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;
import com.artsoft.scb.model.entity.Offerer;

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
		
	@GetMapping(path = "/getByOfferer/{mailOfferer}")
	public ResponseEntity<?> getByOfferer(@PathVariable("mailOfferer") String mailOfferer) {
		JSONObject response = new JSONObject();
		try {
			response.put("Response", convocatoryService.getByOffer(mailOfferer));
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getById/{id}")
	public ResponseEntity<?> post(@PathVariable("id") int id) {
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
	
	@GetMapping(path = "/getByPendingPhases/{mailOfferer}")
	public ResponseEntity<?> getByPendingPhases(@PathVariable("mailOfferer") String mailOfferer) {
		JSONObject response = new JSONObject();
		List<Convocatory> convocatories = new ArrayList<Convocatory>();
		try {
			convocatories = convocatoryService.getByPendingPhases(mailOfferer);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatories);
	}
	
	@GetMapping(path = "/getByOffererState/{mailOfferer}/{state}")
	public ResponseEntity<?> getByOffererState(@PathVariable("mailOfferer") String mailOfferer, @PathVariable("state") int state) {

		List<Convocatory> convocatories;
		try {
			convocatories = convocatoryService.getByOffererState(mailOfferer, state);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatories);
	}
	
	@PostMapping(path = "/edit")
	public ResponseEntity<?> editConvocatory(@RequestBody Convocatory convocatory){
		JSONObject response = new JSONObject();
		try {
			convocatoryService.editConvocatory(convocatory);
			response.put("Response", "Convocatoria editada con éxito");
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getPending")
	public ResponseEntity<?> getByOffererState() {

		List<Convocatory> convocatories;
		try {
			convocatories = convocatoryService.getConvocatoriesWithPhasesToApprove();
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatories);
	}
}


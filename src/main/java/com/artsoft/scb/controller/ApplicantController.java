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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.scb.model.bll.ApplicantService;
import com.artsoft.scb.model.bll.ConvocatoryService;
import com.artsoft.scb.model.bll.PlaceService;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.RejectPlaceStructure;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Place;

@EnableWebMvc
@RestController
@RequestMapping(path = "/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private ConvocatoryService convocatoryService;
	
	@Autowired
	private PlaceService placeService;
		
	@PostMapping()
	public ResponseEntity<?> createApplicant(@RequestBody Applicant applicant) {
		 JSONObject response = new JSONObject();
		try {
			applicantService.createApplicant(applicant);	
			response.put("Response", "Bienvenido, le llegar� un correo para completar su registro.");
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
	/**
	 * Devuelve las convocatorias a las que ha aplicado un solicitante, con excepcion de aquellas a las que ya tiene una plaza
	 * @param mailApplicant
	 * @return
	 */
	@GetMapping(path = "/postulations/{mail:.+}")
	public ResponseEntity<?> getPostulations(@PathVariable("mail") String mailApplicant){
		List<Convocatory> convocatory;
		try {
			convocatory  = convocatoryService.getConvocatoriesOfApplicant(mailApplicant);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatory);
	}
	/**
	 * Devuelve las convocatorias 
	 * @param mailApplicant
	 * @return
	 */
	@GetMapping(path = "/places/{mail:.+}")
	public ResponseEntity<?> getPlaces(@PathVariable("mail") String mailApplicant){
		List<Place> convocatory;
		try {
			convocatory  = convocatoryService.getConvocatoriesOfApplicantWithPlaces(mailApplicant);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatory);
	}
	
	@GetMapping(path = "/notAppliedConvocatories/{mail:.+}")
	public ResponseEntity<?> getNotAppliedConvocatories(@PathVariable("mail") String mailApplicant){
		List<Convocatory> convocatory;
		try {
			convocatory  = convocatoryService.getNotAppliedConvocatories(mailApplicant);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatory);
	}
	
	@PostMapping(path = "/acceptConvocatory/{idPlace}")
	public ResponseEntity<?> acceptPlace(@PathVariable("idPlace") int idPlace){
		JSONObject response = new JSONObject();
		try {
			placeService.acceptPlace(idPlace);
			response.put("Response", "La plaza ha sido aceptada");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping(path = "/rejectPlace")
	public ResponseEntity<?> rejectPlace(@RequestBody RejectPlaceStructure rejectPlaceStructure){
		JSONObject response = new JSONObject();
		try {
			placeService.rejectPlace(rejectPlaceStructure);
			response.put("Response", "La plaza ha sido rechazada");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
}

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
	
	@GetMapping(path = "/getCurrentPhase/{idConvocatory}/{mailApplicant}")
	public ResponseEntity<?> getCurrentPhase(@PathVariable("idConvocatory") int idConvocatory, @PathVariable("idConvocatory") String mailApplicant){
		Phase phase;
		try {
			phase = phaseService.getCurrentPhaseForConvocatoryAndApplicant(idConvocatory, mailApplicant);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(phase);
	}
}

/*
  
 Para esa convocatoria obtiene la fase actual por las fechas de Inicio de fase a Fecha finalizacion de aprobacion de oferente, obteniendo por el aplicante el solicitante-fase.

* Si no hay fase actual: la convocatoria ya cerro, devuelve fase null.

* Si hay fase actual:
   - Si nada en solicitante-fase: no ha aplicado a esta conv. Se retorna la fase 1 y estado: PendienteRegistroDatos.
   
   - Si hay algun registro en solicitante-fase:
        * No tiene registro de solicitante-fase pára esta fase: es porque fue rechazado de una fase anterior. Se retorna la fase actual y estado: RechazadoFaseAnterior.

        * Hay registro solicitante-fase para esta fase: Es porque el usuario sigue estando activo en esta fase. Se retorna la fase y el estado que tiene en solicitante-fase.
        * */ 
  


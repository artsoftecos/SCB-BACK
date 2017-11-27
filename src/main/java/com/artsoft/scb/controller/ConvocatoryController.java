package com.artsoft.scb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artsoft.scb.model.bll.ApplicantDocumentService;
import com.artsoft.scb.model.bll.ConvocatoryDocumentService;
import com.artsoft.scb.model.bll.ConvocatoryService;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;

@RestController
@PreAuthorize("hasAnyRole('ROLE_OFFERER','ROLE_APPLICANT')")
@RequestMapping(path = "/convocatory")
public class ConvocatoryController {
	
	/**
	 * crea la convocatoria
	 */
	
	@Autowired
	private ConvocatoryDocumentService convocatoryDocumentService;
	
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
		
	@GetMapping(path = "/getByOfferer/{mailOfferer:.+}")
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
		
		Convocatory convocatory = null;
		try {
			convocatory = convocatoryService.getById(id);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatory);
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
	
	@GetMapping(path = "/getByPendingPhases/{mailOfferer:.+}")
	public ResponseEntity<?> getByPendingPhases(@PathVariable("mailOfferer") String mailOfferer) {
		List<Convocatory> convocatories = new ArrayList<Convocatory>();
		try {
			convocatories = convocatoryService.getByPendingPhases(mailOfferer);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatories);
	}
	
	@GetMapping(path = "/getByOffererState/{mailOfferer:.+}/{state}")
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
	
	@GetMapping(path = "/getPending/{mailOfferer:.+}")
	public ResponseEntity<?> getByOffererState(@PathVariable("mailOfferer") String mailOfferer) {

		List<Convocatory> convocatories;
		try {
			convocatories = convocatoryService.getConvocatoriesWithPhasesToApprove(mailOfferer);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(convocatories);
	}
	
	@PostMapping(path = "/upload")
	@PreAuthorize("hasRole('ROLE_APPLICANT')")	
	public ResponseEntity<?> uploadDocument(@RequestPart("file")MultipartFile file, 
			@RequestPart("email")String email, @RequestPart("name")String name
			, @RequestPart("idPhase")String idPhase, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {			
			convocatoryDocumentService.uploadDocument(file, name, email, idPhase, request);			
			response.put("Response", "Documento almacenado");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping("/downloadDocument/{idPhase}/{email:.+}/{name}")
	@PreAuthorize("hasRole('ROLE_APPLICANT')")
	public void getDocument(@PathVariable("name")String name,
			@PathVariable("idPhase")String idPhase,	@PathVariable("email")String email,	
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		File document = null;
		try {			
			document = convocatoryDocumentService.getDocument(name,email,idPhase, request);
		}
		catch(Exception ex){
			return;
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
				
		response.addHeader("Content-Disposition", "attachment; filename="+document.getName());
	    response.setContentType(Files.probeContentType(document.toPath()));
	    response.setContentLengthLong(document.length());

	    FileInputStream fis = new FileInputStream(document);
	    int c; 
	    while((c = fis.read()) > -1) {
	        response.getOutputStream().write(c);    
	    }

	    response.flushBuffer();

	    fis.close();
		
		/*HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentDispositionFormData("attachment", document.getName());
		
		
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);*/
	}
}


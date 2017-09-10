package com.artsoft.scb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artsoft.scb.model.bll.ApplicantDocumentService;
import com.artsoft.scb.model.entity.ApplicantDocument;

@RestController
//@PreAuthorize("hasRole('ROLE_APLICANT')")
@RequestMapping(path = "/applicantDocument")
public class ApplicantDocumentController {
	
	@Autowired
	private ApplicantDocumentService applicantDocumentService;
	
	@PostMapping()
	@PreAuthorize("hasRole('ROLE_APPLICANT')")
	public ResponseEntity<?> uploadDocument(@RequestPart("file")MultipartFile file, 
			@RequestPart("email")String email, @RequestPart("name")String name, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {			
			applicantDocumentService.uploadDocument(file, name, email, request);
			response.put("Response", "Documento almacenado");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_APPLICANT')")
	public ResponseEntity<?> deleteDocument(@PathVariable("id")Long id, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {			
			applicantDocumentService.deleteDocument(id, request);
			response.put("Response", "Documento eliminado");
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_APPLICANT')")
	public ResponseEntity<?> getDocuments(String email, HttpServletRequest request) {
		List<ApplicantDocument> documents = new ArrayList<ApplicantDocument>();
		try {			
			documents = applicantDocumentService.getDocuments(email);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body(documents);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_APPLICANT')")
	public ResponseEntity<?> getDocument(@PathVariable("id")Long id, HttpServletRequest request) throws FileNotFoundException {
		File document = null;
		try {			
			document = applicantDocumentService.getDocument(id, request);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentDispositionFormData("attachment", document.getName());

		InputStreamResource isr = new InputStreamResource(new FileInputStream(document));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
	
}

package com.artsoft.scb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artsoft.scb.model.bll.ApplicantDocumentService;
import com.artsoft.scb.model.bll.ApplicantService;
import com.artsoft.scb.model.entity.Applicant;

@RestController
@RequestMapping(path = "/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private ApplicantDocumentService applicantDocumentService;
	
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
	
	/*+ uploadDocument(MultipartFile file, String email)
	+ deleteDocument(String fileName, String email)*/
	
	@PostMapping("/uploadDocument")
	public ResponseEntity<?> uploadDocument(MultipartFile file, String email, HttpServletRequest request) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			applicantDocumentService.uploadDocument(file, path, email);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Documento almacenado");
	}
	
}

package com.artsoft.scb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.DocumentTypeService;
import com.artsoft.scb.model.entity.DocumentType;

@RestController
@RequestMapping(path = "/documentType")
public class DocumentTypeController {
	
	@Autowired
	private DocumentTypeService documentTypeService;
	
	/**
	 * Devuelve los tipos de documento
	 * @return la lista de tipos de documento
	 */
	@GetMapping("/")
	public ResponseEntity<?> get() {		
		List<DocumentType> documentTypeList = documentTypeService.get();			
		return ResponseEntity.status(HttpStatus.OK).body(documentTypeList);
	}
}

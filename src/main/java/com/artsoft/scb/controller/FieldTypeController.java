package com.artsoft.scb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.FieldTypeService;
import com.artsoft.scb.model.entity.FieldType;
import com.artsoft.scb.model.entity.FieldTypeValidation;

@RestController
@RequestMapping(path = "/fieldType")
public class FieldTypeController {

	@Autowired
	private FieldTypeService fieldTypeService;
	
	/**
	 * Devuelve los tipos de campos
	 * @return la lista de tipos de campos
	 */
	@GetMapping()
	public ResponseEntity<?> get() {		
		List<FieldType> fieldTypeList = fieldTypeService.get();			
		return ResponseEntity.status(HttpStatus.OK).body(fieldTypeList);
	}
	
	@GetMapping(path = "/Validations/{id}")
	public ResponseEntity<?> getValidations(@PathVariable("id") int idFieldType) {		
		List<FieldTypeValidation> fieldTypeValidationList = fieldTypeService.getValidations(idFieldType);			
		return ResponseEntity.status(HttpStatus.OK).body(fieldTypeValidationList);
	}
}

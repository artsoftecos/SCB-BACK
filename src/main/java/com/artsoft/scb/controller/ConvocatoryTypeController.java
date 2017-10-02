package com.artsoft.scb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.ConvocatoryTypeService;
import com.artsoft.scb.model.entity.ConvocatoryType;

@RestController
//@PreAuthorize("hasRole('ROLE_OFFERER')")
@RequestMapping(path = "/convocatoryType")
public class ConvocatoryTypeController {

	@Autowired
	private ConvocatoryTypeService convocatoryTypeService;
	
	/**
	 * Devuelve los tipos de documento
	 * @return la lista de tipos de documento
	 */
	@GetMapping()
	public ResponseEntity<?> get() {		
		List<ConvocatoryType> convocatoryTypeList = convocatoryTypeService.get();			
		return ResponseEntity.status(HttpStatus.OK).body(convocatoryTypeList);
	}
	
}

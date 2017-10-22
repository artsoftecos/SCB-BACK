package com.artsoft.scb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.interfaces.IFieldService;
import com.artsoft.scb.model.entity.Field;

@RestController
@RequestMapping(path = "/field")
public class FieldController {
	
	@Autowired
	private IFieldService fieldService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> createField(@RequestBody Field field) {
		
		try {
			fieldService.createField(field);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Field Created!");
	}
	
	@PostMapping(path = "/delete")
	public ResponseEntity<?> deleteField(@RequestBody Field field) {
		
		try {
			fieldService.deleteField(String.valueOf(field.getId()));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Field Deleted!");
	}
	
	@GetMapping(path = "/getByPhase")
	public ResponseEntity<?> getAllField(@PathVariable("idPhase") int idPhase) {
		
		try {
			fieldService.findByPhase(idPhase);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Field Deleted!");
	}

}

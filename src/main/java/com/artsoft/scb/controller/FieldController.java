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
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.interfaces.IFieldService;
import com.artsoft.scb.model.bll.interfaces.IValidationService;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Field;
import com.artsoft.scb.model.entity.Validation;

@RestController
@RequestMapping(path = "/field")
public class FieldController {
	
	@Autowired
	private IFieldService fieldService;

	@Autowired
	private IValidationService validationService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> createField(@RequestBody Field field) {
		JSONObject response = new JSONObject();
		try {
			fieldService.createField(field);
			validationService.linkField(field.getValidation(), field);
			response.put("Response", "Campo creado");
			response.put("field_id", field.getId());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	

	@PostMapping(path = "/edit")
	public ResponseEntity<?> editField(@RequestBody Field field){
		JSONObject response = new JSONObject();
		try {
			fieldService.editField(field);
			response.put("Response", "Campo editado con éxito");
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@PostMapping(path = "/delete/{idField}")
	public ResponseEntity<?> deleteField(@PathVariable("idField") int idField) {
		JSONObject response = new JSONObject();
		try {
//			if(!fieldService.exists(String.valueOf(idField)))
//				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El campo no existe!");
			fieldService.deleteField(idField);
			response.put("Response", "Field Deleted!");
		} catch (IllegalArgumentException ie)	{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Field does not exist!");
		}
		 catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(response.toString());
	}
	
	@GetMapping(path = "/getByPhase/{idPhase}")
	public ResponseEntity<?> getAllField(@PathVariable("idPhase") int idPhase) {
		List<Field> fields;
		try {
			fields = fieldService.findByPhase(idPhase);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(fields);
	}

}

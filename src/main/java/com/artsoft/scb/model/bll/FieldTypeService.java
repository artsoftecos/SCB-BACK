package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.FieldTypeRepository;
import com.artsoft.scb.model.dao.FieldTypeValidationRepository;
import com.artsoft.scb.model.entity.FieldType;
import com.artsoft.scb.model.entity.FieldTypeValidation;

@Service
public class FieldTypeService {

	@Autowired
	FieldTypeRepository fieldTypeRepo;
	
	@Autowired
	FieldTypeValidationRepository fieldTypeValidationRepo;
	
	public List<FieldType> get() {
		return (List<FieldType>) fieldTypeRepo.findAll();
	}
	
	public List<FieldTypeValidation> getValidations(int idField) {
		FieldType fieldType = fieldTypeRepo.findOne(idField);
		List<FieldTypeValidation> listFieldTypeValidation = (List<FieldTypeValidation>) fieldTypeValidationRepo.findByFieldType(fieldType); 
		return listFieldTypeValidation; 
	} 
}

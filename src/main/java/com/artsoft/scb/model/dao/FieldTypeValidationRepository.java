package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.FieldType;
import com.artsoft.scb.model.entity.FieldTypeValidation;

public interface FieldTypeValidationRepository extends CrudRepository<FieldTypeValidation, Integer> {
	List<FieldTypeValidation> findByFieldType(FieldType fieldType);	
}
package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Field;

public interface FieldRespository extends CrudRepository<Field, String> {
	
	public List<Field> findByidPhase(int idPhase);
	Field findById(int id);
	
}

package com.artsoft.scb.model.bll.interfaces;

import java.util.List;

import com.artsoft.scb.model.entity.Field;

public interface IFieldService {
	
	public boolean createField(Field field) throws Exception;
	
	public void deleteField(String idField) throws Exception;
	
	public List<Field> findByPhase(int idPhase) throws Exception;
	
	public List<Field> findAll() throws Exception;
	
	public Field findOne(String id) throws Exception;

}

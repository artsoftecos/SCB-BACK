package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IFieldService;
import com.artsoft.scb.model.dao.FieldRespository;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Field;

@Service
public class FieldService implements IFieldService {

	@Autowired
	FieldRespository fiedlRepo;
		
	@Override
	public boolean createField(Field field) throws Exception {
		
		Field result = fiedlRepo.save(field);
		
		if(result == null) 
			return false;
		return true;
	}
	
	@Override
	public void deleteField(int idField) throws Exception {
		Field field = new Field();
		field.setId(idField);
		fiedlRepo.delete(field);
	}

	@Override
	public List<Field> findByPhase(int idPhase) throws Exception {
		return fiedlRepo.findByidPhase(idPhase);
	}

	@Override
	public List<Field> findAll() throws Exception {
		return (List<Field>) fiedlRepo.findAll();
	}

	@Override
	public Field findOne(String id) throws Exception {
		return fiedlRepo.findOne(id);
	}

	@Override
	public Boolean exists(String id) throws Exception {
		return fiedlRepo.exists(id);
	}

	@Override
	public boolean editField(Field field) throws Exception {
		Field fieldToEdit = fiedlRepo.findById(field.getId());
		fieldToEdit.setName(field.getName());
//		fieldToEdit.setDescription(convocatory.getDescription());
//		fieldToEdit.setNumberBeneficiaries(convocatory.getNumberBeneficiaries());
//		fieldToEdit.setConvocatoryType(convocatory.getConvocatoryType());
//		fieldToEdit.setResultDate(convocatory.getResultDate());
		Field fieldSaved = fiedlRepo.save(fieldToEdit);
		if(fieldSaved == null){
			return false;
		}
		return true;
	}
	

}

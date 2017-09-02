package com.artsoft.scb.model.bll;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IOffererService;
import com.artsoft.scb.model.dao.OffererRepository;
import com.artsoft.scb.model.entity.Offerer;

@Service
public class OffererService extends ExceptionService implements IOffererService {
	
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private OffererRepository oferentRepository;
	
	/**
	 * Crea el oferente
	 * @param offerer el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	public boolean createOferent(Offerer offerer) throws Exception {
		
		validateOferent(offerer);
		offerer.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		
		Offerer oferentSaved = oferentRepository.save(offerer);
		if (oferentSaved == null) {
			return false;
		}
		
		return true;
	}
		
	/**
	 * obtiene todos los oferentes
	 * @return los oferentes.
	 */
	public List<Offerer> getAllOferents() {
		return (ArrayList<Offerer>)oferentRepository.findAll();
	}
	
	/**
	 * Valida el oferente
	 * @param applicant: El oferente
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	private void validateOferent(Offerer offerer) throws Exception {
		
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(offerer);
		
		if (constraintViolations.size() > 0) {		    
		    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
		    	parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());		    	
		    }
		}		
		
		if(parameters.values().size()>0){
			throwException(parameters);
		}
		
		ValidatePassword(offerer);
		ValidateEmail(offerer);
		ValidateNit(offerer);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateNit(Offerer offerer) throws Exception {		
		
		Offerer oferentFound = oferentRepository.findByNit(offerer.getNit());		
		if (oferentFound != null) {
			throwException("documentNumber", "Ya hay un oferente con ese nit.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El oferente
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Offerer offerer) throws Exception {
		
		Offerer oferentSearched = oferentRepository.findByEmail(offerer.getEmail());
		if (oferentSearched != null) {
			throwException("email", "Ya hay un oferente con ese email.");
		}
	}
	
	private void ValidatePassword(Offerer offerer) throws Exception {
		if(offerer.getPassword() == null || offerer.getPassword().isEmpty()) {
			throwException("password", "La clave es requerida.");
		}
		
		if(offerer.getPassword().length() < 3) {
			throwException("password", "La clave debe ser mayor de 3 caracteres.");			
		}
	}	
}

	

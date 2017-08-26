package com.artsoft.scb.model.bll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IOferentService;
import com.artsoft.scb.model.dao.OferentRepository;
import com.artsoft.scb.model.entity.Oferent;

@Service
public class OferentService implements IOferentService {
	
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private OferentRepository oferentRepository;
	
	/**
	 * Crea el oferente
	 * @param oferent el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	public boolean createOferent(Oferent oferent) throws Exception {
		
		validateOferent(oferent);
		oferent.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		
		Oferent oferentSaved = oferentRepository.save(oferent);
		if (oferentSaved == null) {
			return false;
		}
		
		return true;
	}
		
	/**
	 * obtiene todos los oferentes
	 * @return los oferentes.
	 */
	public List<Oferent> getAllOferents() {
		return (ArrayList<Oferent>)oferentRepository.findAll();
	}
	
	/**
	 * Valida el oferente
	 * @param applicant: El oferente
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	private void validateOferent(Oferent oferent) throws Exception {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Oferent>> constraintViolations = validator.validate(oferent);

		if (constraintViolations.size() > 0) {
		    Set<String> violationMessages = new HashSet<String>();

		    for (ConstraintViolation<Oferent> constraintViolation : constraintViolations) {
		        violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
		    }

		    throw new RuntimeException("Oferent is not valid:\n" + StringUtils.join(violationMessages, "\n"));
		}
		
		ValidatePassword(oferent);
		ValidateEmail(oferent);
		ValidateNit(oferent);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateNit(Oferent oferent) throws Exception {		
		
		Oferent oferentFound = oferentRepository.findByNit(oferent.getNit());		
		if (oferentFound != null) {
			throw new Exception("Ya hay un oferente con ese nit.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El oferente
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Oferent oferent) throws Exception {
		
		Oferent oferentSearched = oferentRepository.findByEmail(oferent.getEmail());
		if (oferentSearched != null) {
			throw new Exception("Ya hay un oferente con ese email.");
		}
	}
	
	private void ValidatePassword(Oferent oferent) throws Exception {
		if(oferent.getPassword() == null || oferent.getPassword().isEmpty()) {
			throw new Exception("La clave es requerida.");
		}
		
		if(oferent.getPassword().length() < 3) {
			throw new Exception("La clave debe ser mayor de 3 caracteres.");
		}
	}	
}

	

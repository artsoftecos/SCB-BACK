package com.artsoft.scb.model.bll;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IApplicantService;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.DocumentTypeRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.DocumentType;

@Service
public class ApplicantService implements IApplicantService {
	
	/**
	 * Repository of document type.
	 */
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private ApplicantRepository applicantRepository;
	
	/**
	 * Crea el solicitante
	 * @param applicant el solicitante.
	 * @throws Exception : lanza excepcion si alguna propiedad del solicitante no es valida.
	 */
	public boolean createApplicant(Applicant applicant) throws Exception {
		
		validateApplicant(applicant);
		setDocumentType(applicant);
		applicant.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		
		Applicant applicantSaved = applicantRepository.save(applicant);
		if (applicantSaved == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * asocia el tipo de documento al solicitante
	 * @param applicant: el solicitante
	 */
	private void setDocumentType(Applicant applicant) {		
		DocumentType documentType = documentTypeRepository.findOne(applicant.getDocumentType().getId());
		applicant.setDocumentType(documentType);
	}
	
	/**
	 * Valida el solicitante
	 * @param applicant: El solicitante
	 * @throws Exception : lanza excepcion si alguna propiedad del solicitante no es valida.
	 */
	private void validateApplicant(Applicant applicant) throws Exception {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Applicant>> constraintViolations = validator.validate(applicant);

		if (constraintViolations.size() > 0) {
		    Set<String> violationMessages = new HashSet<String>();

		    for (ConstraintViolation<Applicant> constraintViolation : constraintViolations) {
		        violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
		    }

		    throw new RuntimeException("Applicant is not valid:\n" + StringUtils.join(violationMessages, "\n"));
		}
		
		ValidatePassword(applicant);
		ValidateEmail(applicant);
		ValidateDocuments(applicant);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateDocuments(Applicant applicant) throws Exception {
		/*if (applicant.getDocumentType() == null) {
			throw new Exception("El tipo documento es requerido.");
		}*/
		
		int idDocumentType = applicant.getDocumentType().getId();
		DocumentType documentType = documentTypeRepository.findOne(idDocumentType);
				
		if(documentType == null) {
			throw new Exception("No existe ese tipo documento.");
		}
				
		Applicant applicantSearched = applicantRepository.findByDocumentTypeAndDocumentNumber(documentType, applicant.getDocumentNumber());
		if (applicantSearched != null) {
			throw new Exception("Ya hay un aplicante con ese numero de documento y tipo documento.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El solicitante
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Applicant applicant) throws Exception {
		
		Applicant applicantSearched = applicantRepository.findByEmail(applicant.getEmail());
		if (applicantSearched != null) {
			throw new Exception("Ya hay un aplicante con ese email.");
		}
	}
	
	private void ValidatePassword(Applicant applicant) throws Exception {
		if(applicant.getPassword() == null || applicant.getPassword().isEmpty()) {
			throw new Exception("La clave es requerida.");
		}
		
		if(applicant.getPassword().length() < 3) {
			throw new Exception("La clave debe ser mayor de 3 caracteres.");
		}
	}
}

	

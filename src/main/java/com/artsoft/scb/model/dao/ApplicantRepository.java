package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.DocumentType;

public interface ApplicantRepository extends CrudRepository<Applicant, String> {	
	Applicant findByEmail(String email);
	Applicant findByDocumentTypeAndDocumentNumber(DocumentType documentType, String documentNumber);	
}
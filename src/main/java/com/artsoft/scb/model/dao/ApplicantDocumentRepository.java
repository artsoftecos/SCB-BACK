package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantDocument;

public interface ApplicantDocumentRepository extends CrudRepository<ApplicantDocument, Long> {	
	ApplicantDocument findByNameAndApplicant(String name, Applicant applicant);
	List<ApplicantDocument> findByApplicant(Applicant applicant);
}

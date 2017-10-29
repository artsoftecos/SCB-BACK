package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.PostulationRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Postulation;

@Service
public class PostulationService extends ExceptionService{
	
	@Autowired
	private PostulationRepository postulationRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	public List<Postulation> getPostulationsByEmail(String applicantMail){
		Applicant applicant = applicantRepository.findByEmail(applicantMail);
		return postulationRepository.findByApplicant(applicant);
	}

}

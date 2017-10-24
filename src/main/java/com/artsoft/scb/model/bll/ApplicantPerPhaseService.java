package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IApplicantPerPhaseService;
import com.artsoft.scb.model.dao.ApplicantPerPhaseRepository;
import com.artsoft.scb.model.dao.ApplicantPerPhaseStateRepository;
import com.artsoft.scb.model.entity.ApplicantPerPhase;
import com.artsoft.scb.model.entity.ApplicantPerPhaseState;

@Service
public class ApplicantPerPhaseService extends ExceptionService implements IApplicantPerPhaseService{
	
	@Autowired
	private ApplicantPerPhaseRepository applicantPerPhaseRepository;
	
	@Autowired
	private ApplicantPerPhaseStateRepository applicantPerPhaseStateRepository;
	
	
	public List<ApplicantPerPhase> getApplicantPerPhaseByState(int idState){
		ApplicantPerPhaseState apPerPhaseState = applicantPerPhaseStateRepository.getById(idState);
		List<ApplicantPerPhase> applicantsPerPhases = applicantPerPhaseRepository.getByApplicantPerPhaseState(apPerPhaseState);
		return applicantsPerPhases;
	}
}

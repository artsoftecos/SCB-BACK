package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IApplicantPerPhaseService;
import com.artsoft.scb.model.dao.ApplicantPerPhaseRepository;
import com.artsoft.scb.model.dao.ApplicantPerPhaseStateRepository;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.PhaseRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantPerPhase;
import com.artsoft.scb.model.entity.ApplicantPerPhaseState;
import com.artsoft.scb.model.entity.Phase;

@Service
public class ApplicantPerPhaseService extends ExceptionService implements IApplicantPerPhaseService{
	
	@Autowired
	private ApplicantPerPhaseRepository applicantPerPhaseRepository;
	
	@Autowired
	private ApplicantPerPhaseStateRepository applicantPerPhaseStateRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private PhaseRepository phaseRepository;
	
	private final int STATE_REJECTED = 3;
	
	private final int STATE_PENDING = 2;
	
	public List<ApplicantPerPhase> getApplicantPerPhaseByState(int idState){
		ApplicantPerPhaseState apPerPhaseState = applicantPerPhaseStateRepository.getById(idState);
		List<ApplicantPerPhase> applicantsPerPhases = applicantPerPhaseRepository.getByApplicantPerPhaseState(apPerPhaseState);
		return applicantsPerPhases;
	}
	
	public ApplicantPerPhase getApplicantPerPhaseByApplicantAndPhase(Applicant applicant, Phase phase){
		ApplicantPerPhase apPerPhase = applicantPerPhaseRepository.getByApplicantAndPhase(applicant, phase);
		return apPerPhase;
	}
	
	public void rejectApplicantPerPhase(ApplicantPerPhase applicantPerPhase){		
		ApplicantPerPhaseState state = applicantPerPhaseStateRepository.getById(STATE_REJECTED);
		applicantPerPhase.setApplicantPerPhaseState(state);
		applicantPerPhaseRepository.save(applicantPerPhase);		
	}
	
	public boolean asociateApplicantToAPhase(ApplicantPerPhase applicantPerPhase){
		Applicant applicantToAsociate = applicantRepository.findByEmail(applicantPerPhase.getApplicant().getEmail());
		Phase phaseToAsociate = phaseRepository.findById(applicantPerPhase.getPhase().getId());
		ApplicantPerPhaseState applicantPerPhaseState = applicantPerPhaseStateRepository.getById(STATE_PENDING);
		
		
		applicantPerPhase.setApplicant(applicantToAsociate);
		applicantPerPhase.setPhase(phaseToAsociate);
		applicantPerPhase.setApplicantPerPhaseState(applicantPerPhaseState);
		
		ApplicantPerPhase applicantPerPhaseToSave = applicantPerPhaseRepository.save(applicantPerPhase);
		if(applicantPerPhaseToSave == null){
			return false;
		}
		
		return true;
	}
	
	public ApplicantPerPhase getApplicantAsociatedToAPhase(int idApplicantPerPhase){
		ApplicantPerPhase applicantPerPhaseToReturn = applicantPerPhaseRepository.getById(idApplicantPerPhase);
		return applicantPerPhaseToReturn;
	}
}

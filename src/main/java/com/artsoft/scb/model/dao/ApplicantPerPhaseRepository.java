package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantPerPhase;
import com.artsoft.scb.model.entity.ApplicantPerPhaseState;
import com.artsoft.scb.model.entity.Phase;

public interface ApplicantPerPhaseRepository extends CrudRepository<ApplicantPerPhase, Integer> {
	List<ApplicantPerPhase> getByApplicantPerPhaseState (ApplicantPerPhaseState applicantPerPhaseState);
	ApplicantPerPhase getByApplicantAndPhase (Applicant applicant, Phase phase);
	ApplicantPerPhase getById(int id);
	List<ApplicantPerPhase> getByPhase(Phase phase);
}

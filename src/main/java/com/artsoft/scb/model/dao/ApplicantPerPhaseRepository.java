package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.ApplicantPerPhase;
import com.artsoft.scb.model.entity.ApplicantPerPhaseState;

public interface ApplicantPerPhaseRepository extends CrudRepository<ApplicantPerPhase, Integer> {
	List<ApplicantPerPhase> getByApplicantPerPhaseState (ApplicantPerPhaseState applicantPerPhaseState);
}

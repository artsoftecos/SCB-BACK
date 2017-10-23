package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.ApplicantPerPhaseState;

public interface ApplicantPerPhaseStateRepository extends CrudRepository<ApplicantPerPhaseState, Integer>{
	
	ApplicantPerPhaseState getById(int id);

}

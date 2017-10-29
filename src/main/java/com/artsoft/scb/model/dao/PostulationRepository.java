package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Postulation;

public interface PostulationRepository extends CrudRepository<Postulation, Integer> {
	List<Postulation> findByApplicant(Applicant applicant);
}

package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Place;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
	List<Place> findByApplicant(Applicant applicant);
	Place findByConvocatory(Convocatory convocatory);
	Place findById(int idPlace);

}

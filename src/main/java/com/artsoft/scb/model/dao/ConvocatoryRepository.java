package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;
import com.artsoft.scb.model.entity.Offerer;

public interface ConvocatoryRepository extends CrudRepository<Convocatory, Integer>{
	Convocatory findById(int id);
	List<Convocatory> findByOfferer(Offerer offerer);
	List<Convocatory> findByConvocatoryState(ConvocatoryState convocatoryState);
}
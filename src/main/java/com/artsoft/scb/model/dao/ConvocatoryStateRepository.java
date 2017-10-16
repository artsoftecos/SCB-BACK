package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.ConvocatoryState;

public interface ConvocatoryStateRepository extends CrudRepository<ConvocatoryState, Integer>{
	ConvocatoryState findById(int id);
}

package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.ConvocatoryType;

public interface ConvocatoryTypeRepository extends CrudRepository<ConvocatoryType, Integer> {

	ConvocatoryType findById(int id);
}


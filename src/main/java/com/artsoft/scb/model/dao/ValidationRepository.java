package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Validation;

public interface ValidationRepository extends CrudRepository<Validation, String> {
}

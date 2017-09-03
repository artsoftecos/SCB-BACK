package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Offerer;

public interface OffererRepository extends CrudRepository<Offerer, String> {
	Offerer findByEmail(String email);
	Offerer findByNit(String nit);
}

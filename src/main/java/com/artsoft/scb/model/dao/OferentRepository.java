package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Oferent;

public interface OferentRepository extends CrudRepository<Oferent, String> {
	Oferent findByEmail(String email);
	Oferent findByNit(String nit);
}

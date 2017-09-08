package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Offerer;
import com.artsoft.scb.model.entity.OffererState;

public interface OffererRepository extends CrudRepository<Offerer, String> {
	Offerer findByEmail(String email);
	Offerer findByNit(String nit);
	List<Offerer> findByOffererState(OffererState offererState);
}

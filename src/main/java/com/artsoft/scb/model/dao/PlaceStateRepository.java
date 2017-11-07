package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.PlaceState;

public interface PlaceStateRepository extends CrudRepository<PlaceState, Integer> {
	public PlaceState findById(int id);

}

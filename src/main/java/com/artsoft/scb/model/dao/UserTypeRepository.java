package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
	public abstract UserType findByUser(String email);
}

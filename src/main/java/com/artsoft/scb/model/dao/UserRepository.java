package com.artsoft.scb.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	User findByApplicant(Applicant applicant);
	User findByToken(String token);
	public abstract User findByEmail(String email);
}
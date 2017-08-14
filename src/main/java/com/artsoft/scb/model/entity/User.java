package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class User {

	@Id
	@Column(name = "email", unique = true, nullable = false, length = 60)
	private String email;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
}

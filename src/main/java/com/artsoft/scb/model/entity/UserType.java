package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TipoUsuario", uniqueConstraints = @UniqueConstraint(
		columnNames = {"rol", "email"}))
public class UserType {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer idRol;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "usertype-user")
	@JoinColumn(name = "email", nullable = false)
	private User user;
	
	@Column(name = "rol")
	private String role;
	
	
	public UserType(User user, String rol) {
		super();
		this.user = user;
		this.role = rol;
	}
	
	public UserType(){}
	
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRol() {
		return role;
	}
	public void setRol(String rol) {
		this.role = rol;
	}
}

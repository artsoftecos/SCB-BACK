package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;	
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "Enabled")
	private boolean enabled;
	
	@Column(name = "Token")
	private String token;
	
	@OneToOne(mappedBy="user")	
	private Applicant applicant;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	} 
}

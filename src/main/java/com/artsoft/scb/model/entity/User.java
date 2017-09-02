package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class User {

	@Id
	@Column(name = "email", unique = true, nullable = false, length = 60)
    private String email;	
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "token")
	private String token;
	
	@OneToOne(mappedBy="user")	
	private Applicant applicant;

	@OneToOne(mappedBy="user")	
	private Offerer offerent;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
	private UserType userType;

	
	
	
	public User(String email, String password, boolean enabled) {
		super();
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String email, String password, boolean enabled, UserType userType) {
		super();
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.userType = userType;
	}
	
	public User(){}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

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
	
	public Offerer getOfferent() {
		return offerent;
	}

	public void setOfferent(Offerer offerent) {
		this.offerent = offerent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

package com.artsoft.scb.model.entity;

public class UserLoginDetails {
	private String role;
	private String fullName;
	private boolean enabled;
	private String email;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullNameAplicant(String firstName, String secondName, String firsLastName, String secondLastName) {
		this.fullName = firstName + " " + secondLastName + " " + firsLastName + " " + secondLastName;
	}
	public void setNameOferent(String name){
		this.fullName = name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

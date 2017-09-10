package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Credencial")
public class AWScredential {
	
	@Id	
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Llave")
	private String key;
	
	@Column(name = "Valor")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

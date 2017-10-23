package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TipoValidacion")
public class ValidationType {
	@Id
	@NotNull(message = "El id es requerido")
	@Column(name = "Id")
	private int id;

	@NotNull(message = "El nombre es requerido")
	@NotEmpty(message = "El nombre es requerido")
	@Column(name = "Nombre")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

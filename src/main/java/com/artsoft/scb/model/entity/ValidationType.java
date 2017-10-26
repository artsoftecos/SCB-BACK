package com.artsoft.scb.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TipoValidacion")
public class ValidationType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private int id;

	@NotNull(message = "El nombre es requerido")
	@NotEmpty(message = "El nombre es requerido")
	@Column(name = "Nombre")
	private String name;

	@JsonBackReference(value = "validationType-fieldTypeValidation")
	@OneToMany(mappedBy = "validationType")
	private Set<FieldTypeValidation> fieldTypeValidation = new HashSet<FieldTypeValidation>();
	
	@Column(name = "Expresion")
	private String expression;

	public Set<FieldTypeValidation> getFieldTypeValidation() {
		return fieldTypeValidation;
	}

	public void setFieldTypeValidation(Set<FieldTypeValidation> fieldTypeValidation) {
		this.fieldTypeValidation = fieldTypeValidation;
	}

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

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

}

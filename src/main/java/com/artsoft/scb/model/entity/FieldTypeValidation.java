package com.artsoft.scb.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ValidacionTipoCampo")
public class FieldTypeValidation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull(message = "El id es requerido")
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "idTipoValidacion")
	private ValidationType validationType;
	
	@JsonBackReference(value = "fieldTypeValidation-validation")
	@OneToMany(mappedBy = "fieldTypeValidation")
	private Set<Validation> validation = new HashSet<Validation>();
	
	@ManyToOne()
	@JoinColumn(name = "idTipoCampo")
	private FieldType fieldType = new FieldType();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ValidationType getValidationType() {
		return validationType;
	}

	public void setValidationType(ValidationType validationType) {
		this.validationType = validationType;
	}

	public Set<Validation> getValidation() {
		return validation;
	}

	public void setValidation(Set<Validation> validation) {
		this.validation = validation;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
}

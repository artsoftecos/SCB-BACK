package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ValidacionTipoCampo")
public class FieldTypeValidation {

	@Id
	@NotNull(message = "El id es requerido")
	@Column(name = "Id")
	private int id;

	@NotNull(message = "El FieldTypeId es requerido")
	@Column(name = "FieldTypeId")
	private int fieldTypeId;
	
	@NotNull(message = "El validationTypeId es requerido")
	@Column(name = "ValidationTypeId")
	private int validationTypeId;
	
	@JsonBackReference(value = "fieldTypeValidation-validationType")
	@OneToOne(mappedBy = "validationType")
	private ValidationType validationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFieldTypeId() {
		return fieldTypeId;
	}

	public void setFieldTypeId(int fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}

	public int getValidationTypeId() {
		return validationTypeId;
	}

	public void setValidationTypeId(int validationTypeId) {
		this.validationTypeId = validationTypeId;
	}

	public ValidationType getValidationType() {
		return validationType;
	}

	public void setValidationType(ValidationType validationType) {
		this.validationType = validationType;
	}
}

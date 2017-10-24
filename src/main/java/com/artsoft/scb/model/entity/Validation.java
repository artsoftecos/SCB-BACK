package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Validacion")
public class Validation {
	
	@Id	
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JsonBackReference(value = "validation-field")
	@JoinColumn(name = "idField")
	private Field field;
	
	@JsonBackReference(value = "validation-fieldTypeValidation")
	@ManyToOne()
	@JoinColumn(name ="idTipoValidacionCampo")
	private FieldTypeValidation fieldTypeValidation;
	
	@Column(name = "Valor")
	private String value;
	
	@Column(name = "MensajeError")
	private String errorMessage;
	
	public FieldTypeValidation getFieldTypeValidation() {
		return fieldTypeValidation;
	}

	public void setFieldTypeValidation(FieldTypeValidation fieldTypeValidation) {
		this.fieldTypeValidation = fieldTypeValidation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

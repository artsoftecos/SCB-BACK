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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TipoCampo")
public class FieldType {

	@Id	
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "fieldType")
	@JsonBackReference(value = "fieldType-field")
	private Set<Field> field = new HashSet<Field>();
	
	@OneToMany(mappedBy = "fieldType")
	@JsonBackReference(value = "field-fieldType")
	private Set<FieldTypeValidation> fieldTypeValidation = new HashSet<FieldTypeValidation>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Field> getField() {
		return field;
	}

	public void setField(Set<Field> field) {
		this.field = field;
	}

	public Set<FieldTypeValidation> getFieldTypeValidation() {
		return fieldTypeValidation;
	}

	public void setFieldTypeValidation(Set<FieldTypeValidation> fieldTypeValidation) {
		this.fieldTypeValidation = fieldTypeValidation;
	}
}

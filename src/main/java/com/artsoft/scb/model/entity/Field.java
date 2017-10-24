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


@Entity
@Table(name = "Campo")
public class Field {
	
	@Id	
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "IdFase")
	private int idPhase;
	
	@Column(name = "Nombre", nullable = false, length = 60)
	private String name;
	
	@Column(name = "Obligatorio", nullable = false, length = 60)
	private boolean obligatory;
	
	@Column(name = "Tipo", nullable = false, length = 60)
	private String type;
	
	@Column(name = "Valor", nullable = false, length = 60)
	private String value;
	
	@Column(name = "SeleccionMultiple", nullable = false, length = 60)
	private boolean multipleSelection;
	
	@ManyToOne()
	@JoinColumn(name = "idTipoCampo")
	private FieldType fieldType = new FieldType();
	
	@OneToOne(mappedBy = "field")
	private Validation validation;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPhase() {
		return idPhase;
	}

	public void setIdPhase(int idPhase) {
		this.idPhase = idPhase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isObligatory() {
		return obligatory;
	}

	public void setObligatory(boolean obligatory) {
		this.obligatory = obligatory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isMultipleSelection() {
		return multipleSelection;
	}

	public void setMultipleSelection(boolean multipleSelection) {
		this.multipleSelection = multipleSelection;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
}

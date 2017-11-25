package com.artsoft.scb.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;


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
	
	@Column(name = "Orden", nullable = false, length = 60)
	private int order;
	
	@Column(name = "Obligatorio", nullable = false, length = 60)
	private boolean obligatory;
	
	@Column(name = "Valor", nullable = false, length = 60)
	private String value;
	
	@Column(name = "SeleccionMultiple", nullable = false, length = 60)
	private boolean multipleSelection;
	
	@ManyToOne
    @JoinColumn(name = "IdFieldType")
	private FieldType fieldType;
	
	@Autowired(required = false)
	@OneToOne(mappedBy = "field", cascade = {CascadeType.ALL,CascadeType.REMOVE},orphanRemoval=true)
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
}

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
@Table(name = "TipoConvocatoria")
public class ConvocatoryType {
	
	@Id	
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre", nullable = false, length = 60)	
	private String name;
	
	@JsonBackReference(value = "convocatory-convocatoryType")
	@OneToMany(mappedBy = "convocatoryType")
	private Set<Convocatory> convocatories = new HashSet<Convocatory>();

	public Set<Convocatory> getConvocatories() {
		return convocatories;
	}

	public void setConvocatories(Set<Convocatory> convocatories) {
		this.convocatories = convocatories;
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
}

package com.artsoft.scb.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "EstadoOferente")
public class OffererState {
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "descripcion")
	private String description;
	
	@OneToMany(mappedBy = "offererState")
	@JsonBackReference(value = "offererState-offerer")
	private Set<Offerer>  offerers = new HashSet<Offerer>();;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Offerer> getOfferers() {
		return offerers;
	}

	public void setOfferers(Set<Offerer> offerers) {
		this.offerers = offerers;
	}

	

}

package com.artsoft.scb.model.entity;

import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Convocatoria")
public class Convocatory {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre", nullable = false, length = 60)
	private String name;
	
	@Column(name = "Descripcion", nullable = false, length = 1000)
	private String description;
	
	@Column(name = "NumeroBeneficiarios", nullable = false)
	private int numberBeneficiaries;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaResultado", nullable = false)
	private Timestamp resultDate;
	
	@ManyToOne
	@JoinColumn(name = "MailOferente")
	private Offerer offerer;
	
	@ManyToOne
	@JoinColumn(name = "IdTipo")
	private ConvocatoryType convocatoryType;

	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private ConvocatoryState convocatoryState;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaPublicacionResultados", nullable = false)
	private Timestamp resultDate;
	
	@OneToMany(mappedBy = "convocatory")
	private Set<Phase> phases = new HashSet<Phase>();
	
	@OneToOne(mappedBy = "convocatory")
	private Place place;
	
	@OneToOne(mappedBy = "convocatory")
	private Postulation postulation;
	
	
	
	public Timestamp getResultDate() {
		return resultDate;
	}

	public void setResultDate(Timestamp resultDate) {
		this.resultDate = resultDate;
	}

	public Postulation getPostulation() {
		return postulation;
	}

	public void setPostulation(Postulation postulation) {
		this.postulation = postulation;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Set<Phase> getPhases() {
		return phases;
	}

	public void setPhases(Set<Phase> phases) {
		this.phases = phases;
	}

	public ConvocatoryState getConvocatoryState() {
		return convocatoryState;
	}

	public void setConvocatoryState(ConvocatoryState convocatoryState) {
		this.convocatoryState = convocatoryState;
	}

	public ConvocatoryType getConvocatoryType() {
		return convocatoryType;
	}

	public void setConvocatoryType(ConvocatoryType convocatoryType) {
		this.convocatoryType = convocatoryType;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberBeneficiaries() {
		return numberBeneficiaries;
	}

	public void setNumberBeneficiaries(int numberBeneficiaries) {
		this.numberBeneficiaries = numberBeneficiaries;
	}

	public Offerer getOfferer() {
		return offerer;
	}

	public void setOfferer(Offerer offerer) {
		this.offerer = offerer;
	}
	
	
	
	
	

}

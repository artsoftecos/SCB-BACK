package com.artsoft.scb.model.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
		
	@ManyToOne
	@JoinColumn(name = "MailOferente")
	private Offerer offerer;
	
	@ManyToOne
	@JoinColumn(name = "IdTipo")
	private ConvocatoryType convocatoryType;

	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private ConvocatoryState convocatoryState;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "FechaPublicacionResultados", nullable = false)
	private Date resultDate;
		

	@JsonBackReference(value = "convocatory-phase")
	@OneToMany(mappedBy = "convocatory")
	private Set<Phase> phases = new HashSet<Phase>();
	
	@JsonBackReference(value = "convocatory-place")
	@OneToMany(mappedBy = "convocatory")
	private Set<Place> places = new HashSet<Place>();
		
	@OneToMany(mappedBy = "convocatory")
	private Set<Postulation> postulations = new HashSet<Postulation>();
		
	
	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	public Set<Postulation> getPostulations() {
		return postulations;
	}

	public void setPostulations(Set<Postulation> postulations) {
		this.postulations = postulations;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
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

	@Override
	public String toString() {
		return "Convocatory [id=" + id + ", name=" + name + ", description=" + description + ", numberBeneficiaries="
				+ numberBeneficiaries + ", offerer=" + offerer + ", convocatoryType=" + convocatoryType
				+ ", convocatoryState=" + convocatoryState + ", resultDate=" + resultDate + ", phases=" + phases + "]";
				//+ ", place=" + place + ", postulation=" + postulation + "]";
	}
}

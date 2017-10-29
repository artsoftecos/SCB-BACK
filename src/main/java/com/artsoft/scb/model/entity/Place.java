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
@Table(name = "Plaza")
public class Place {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "mail_solicitante", nullable = false, unique = false)
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name = "id_convocatoria", nullable = false)
	private Convocatory convocatory;
	
	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private PlaceState placeState;
	
	@Column(name = "CausalRechazo", nullable = true, length = 300)
	private String rejectionCause;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}

	public PlaceState getPlaceState() {
		return placeState;
	}

	public void setPlaceState(PlaceState placeState) {
		this.placeState = placeState;
	}

	public String getRejectionCause() {
		return rejectionCause;
	}

	public void setRejectionCause(String rejectionCause) {
		this.rejectionCause = rejectionCause;
	}
	
	
}

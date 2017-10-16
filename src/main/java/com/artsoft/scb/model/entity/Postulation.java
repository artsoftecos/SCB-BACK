package com.artsoft.scb.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Postulacion")
public class Postulation {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "mail_solicitante", nullable = false)
	private Applicant applicant;
	
	@OneToOne
	@JoinColumn(name = "id_convocatoria", nullable = false)
	private Convocatory convocatory;

	@Column(name = "Fecha_Postulacion", nullable = false)
	private Timestamp postulationDate;

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

	public Timestamp getPostulationDate() {
		return postulationDate;
	}

	public void setPostulationDate(Timestamp postulationDate) {
		this.postulationDate = postulationDate;
	}
	
	
}

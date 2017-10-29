package com.artsoft.scb.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Postulacion")
public class Postulation {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "mail_solicitante", nullable = false)
	private Applicant applicant;
	
	@JsonBackReference(value = "postulation-convocatory")
	@OneToOne
	@JoinColumn(name = "id_convocatoria", nullable = false)
	private Convocatory convocatory;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "Fecha_Postulacion", nullable = false)
	private Date postulationDate;

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

	public Date getPostulationDate() {
		return postulationDate;
	}

	public void setPostulationDate(Date postulationDate) {
		this.postulationDate = postulationDate;
	}
	
	
}

package com.artsoft.scb.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Fase")
public class Phase {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre", nullable = false, length = 60)
	private String name;
	
	@Column(name= "Descripcion", nullable = false, length = 1000)
	private String description;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaInicio", nullable = false)
	private Timestamp startDate;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaFinalizacion", nullable = false)
	private Timestamp endDate;
	
	@ManyToOne
	@JoinColumn(name = "IdConvocatoria")
	private Convocatory convocatory;
	
	@OneToOne(mappedBy = "phase")
	private ApplicantPerPhase applicantPerPhase;

	public ApplicantPerPhase getApplicantPerPhase() {
		return applicantPerPhase;
	}

	public void setApplicantPerPhase(ApplicantPerPhase applicantPerPhase) {
		this.applicantPerPhase = applicantPerPhase;
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}
	
	
	
	
}

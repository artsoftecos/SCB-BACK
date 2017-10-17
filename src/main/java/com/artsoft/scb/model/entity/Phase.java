package com.artsoft.scb.model.entity;

import java.sql.Date;
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
	
	@Column(name = "FechaInicio", nullable = false)
	private Date startDate;
	
	@Column(name = "FechaFinalizacion", nullable = false)
	private Date endDate;
	
	@Column(name = "FechaInicioAprobacion", nullable = false)
	private Date startApprovalDate;
	
	@ManyToOne
	@JoinColumn(name = "IdConvocatoria")
	private Convocatory convocatory;
	
	@OneToOne(mappedBy = "phase")
	private ApplicantPerPhase applicantPerPhase;

	


	public Date getStartApprovalDate() {
		return startApprovalDate;
	}

	public void setStartApprovalDate(Date startApprovalDate) {
		this.startApprovalDate = startApprovalDate;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}
	
	
	
	
}

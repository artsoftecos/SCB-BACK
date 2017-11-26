package com.artsoft.scb.model.entity;


import java.sql.Timestamp;
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

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Fase")
public class Phase implements Comparable<Phase> {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre", nullable = false, length = 60)
	private String name;
	
	@Column(name= "Descripcion", nullable = false, length = 1000)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "FechaInicio", nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "FechaFinalizacion", nullable = false)
	private Date endDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "FechaInicioAprobacion", nullable = false)
	private Date startApprovalDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "es-ES", timezone = "America/Bogota")
	@Column(name = "FechaCierreAprobacion", nullable = false)
	private Date endApprovalDate;
	
	@ManyToOne
	@JoinColumn(name = "IdConvocatoria")
	private Convocatory convocatory;
	
	@OneToMany(mappedBy = "phase")
	private Set<ApplicantPerPhase> applicantPerPhase = new HashSet<ApplicantPerPhase>();

	
	public Date getEndApprovalDate() {
		return endApprovalDate;
	}

	public void setEndApprovalDate(Date endApprovalDate) {
		this.endApprovalDate = endApprovalDate;
	}

	public Date getStartApprovalDate() {
		return startApprovalDate;
	}

	public void setStartApprovalDate(Date startApprovalDate) {
		this.startApprovalDate = startApprovalDate;
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

	public Set<ApplicantPerPhase> getApplicantPerPhase() {
		return applicantPerPhase;
	}

	public void setApplicantPerPhase(Set<ApplicantPerPhase> applicantPerPhase) {
		this.applicantPerPhase = applicantPerPhase;
	}

	@Override
	public int compareTo(Phase p) {
		if(startDate.before(p.getStartDate())){
			return -1;
		}
		
		if(startDate.after(p.getStartDate())){
			return 1;
		}
		
		return 0;
	}
	
	
	
	
}

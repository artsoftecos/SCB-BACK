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

@Entity
@Table(name = "estado_solicitante_por_fase")
public class ApplicantPerPhaseState {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "Nombre", nullable = false, length = 20)
	private String name;
	
	@OneToMany(mappedBy = "applicantPerPhaseState")
	private Set<ApplicantPerPhase> applicantsPerPhase = new HashSet<ApplicantPerPhase>();

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

	public Set<ApplicantPerPhase> getApplicantsPerPhase() {
		return applicantsPerPhase;
	}

	public void setApplicantsPerPhase(Set<ApplicantPerPhase> applicantsPerPhase) {
		this.applicantsPerPhase = applicantsPerPhase;
	}

}
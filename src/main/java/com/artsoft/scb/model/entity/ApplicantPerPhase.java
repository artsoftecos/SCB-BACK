package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SolicitantePorFase")
public class ApplicantPerPhase {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "mail_solicitante", nullable = false)
	private Applicant applicant;

	@OneToOne
	@JsonBackReference(value = "applicantPerPhase-phase")
	@JoinColumn(name = "id_fase", nullable = false)
	private Phase phase;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idEstado")
//	@JsonBackReference(value = "applicantPerPhase-applicantPerPhaseState")
	private ApplicantPerPhaseState applicantPerPhaseState;

	@Column(name = "answers", columnDefinition = "TEXT")
	private String answers;

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public ApplicantPerPhaseState getApplicantPerPhaseState() {
		return applicantPerPhaseState;
	}

	public void setApplicantPerPhaseState(ApplicantPerPhaseState applicantPerPhaseState) {
		this.applicantPerPhaseState = applicantPerPhaseState;
	}

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

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

}

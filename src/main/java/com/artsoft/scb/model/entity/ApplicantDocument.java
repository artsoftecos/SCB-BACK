package com.artsoft.scb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DocumentoSolicitante")
public class ApplicantDocument{
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;	
	
	@NotNull(message = "El nombre es requerido")
	@NotEmpty(message = "El nombre es requerido")
	@Column(name = "Nombre")
	private String name;
	
	@NotNull(message = "La ruta es requerida")
	@NotEmpty(message = "La ruta es requerida")
	@Column(name = "NombreArchivo")
	private String fileName;
	
	@ManyToOne
	@JoinColumn(name="IdSolicitante")
	private Applicant applicant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
}

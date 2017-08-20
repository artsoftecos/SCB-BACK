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
	@Column(name = "Url")
	private String path;
	
	@ManyToOne
	@JoinColumn(name="IdSolicitante")
	private Applicant applicant;
}

package com.artsoft.scb.model.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Solicitante")
public class Applicant {
	
	@Id	
	@NotNull(message = "El email es requerido")
	@Email(message = "El email no es valido")
	@Column(name = "Correo")
	private String email;	
	
	@NotNull(message = "El tipo documento es requerido")
	@ManyToOne
    @JoinColumn(name = "IdTipoDocumento")
	private DocumentType documentType;
	
	@NotNull(message = "El numero de documento es requerido")
	@NotEmpty(message = "El numero de documento es requerido")
	@Column(name = "NumeroDocumento")
	private String documentNumber;	
	
	@NotNull(message = "El primer nombre es requerido")
	@NotEmpty(message = "El primer nombre es requerido")
	@Column(name = "PrimerNombre")
	private String firstName;
	
	@Column(name = "SegundaNombre")
	private String secondName;
	
	@NotNull(message = "El primer apellido es requerido")
	@NotEmpty(message = "El primer apellido es requerido")
	@Column(name = "PrimerApellido")
	private String firstLastName;
	
	@Column(name = "SegundoApellido")
	private String secondLastName;
	
	@Column(name = "Direccion")
	private String address;
	
	@Column(name = "Telefono")
	private String telephone;
	
	@Column(name = "Celular")
	private String cellphone;
	
	@Transient
	private String password;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaRegistro")
	private Timestamp dateRegister;	
		 
	@OneToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value = "applicant-user")	
	@JoinColumn(name="IdUsuario")
	private User user; 
	
	@JsonBackReference(value = "applicantDocument-applicant")
	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
	private Set<ApplicantDocument> applicantDocuments = new HashSet<ApplicantDocument>();
	
	
	@OneToMany(mappedBy = "applicant")
	private Set<ApplicantPerPhase> applicantPerPhase = new HashSet<ApplicantPerPhase>();
	
	@JsonBackReference(value = "applicant-place")
	@OneToMany(mappedBy = "applicant")
	private Set<Place> places = new HashSet<Place>();
	
	@JsonBackReference(value = "applicant-postulation")
	@OneToMany(mappedBy = "applicant")
	private Set<Postulation> postulations = new HashSet<Postulation>();
	

	public Set<Postulation> getPostulations() {
		return postulations;
	}

	public void setPostulations(Set<Postulation> postulations) {
		this.postulations = postulations;
	}

	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstLastName() {
		return firstLastName;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Timestamp dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Set<ApplicantDocument> getApplicantDocuments() {
		return applicantDocuments;
	}

	public void setApplicantDocuments(Set<ApplicantDocument> applicantDocuments) {
		this.applicantDocuments = applicantDocuments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Set<ApplicantPerPhase> getApplicantPerPhase() {
		return applicantPerPhase;
	}

	private void setApplicantPerPhase(Set<ApplicantPerPhase> applicantPerPhase) {
		this.applicantPerPhase = applicantPerPhase;
	}
}


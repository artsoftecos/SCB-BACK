package com.artsoft.scb.model.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "Oferente")
public class Offerer {
	
	@Id	
	@NotNull(message = "El email es requerido")
	@Email(message = "El email no es valido")
	@Column(name = "Correo")
	private String email;	
	
	@NotNull(message = "El nit es requerido")
	@NotEmpty(message = "El nit es requerido")
	@Column(name = "Nit")
	private String nit;	
	
	@NotNull(message = "El nombre es requerido")
	@NotEmpty(message = "El nombre es requerido")
	@Column(name = "Nombre")
	private String name;
		
	@Column(name = "Direccion")
	private String address;
	
	@Column(name = "Telefono")
	private String telephone;
	
	@Column(name = "RepresentanteLegal")
	private String legalRepresentative;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value = "offerent-user")	
	@JoinColumn(name="IdUsuario")
	private User user; 
	
	@Transient
	private String password;
	
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")	
	@Column(name = "FechaRegistro")
	private Timestamp dateRegister;
	
	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private OffererState offererState;

	@JsonBackReference(value="convocatory-offerer")
	@OneToMany(mappedBy = "offerer")
	private Set<Convocatory> convocatories = new HashSet<Convocatory>();
	
	public Set<Convocatory> getConvocatories() {
		return convocatories;
	}

	public void setConvocatories(Set<Convocatory> convocatories) {
		this.convocatories = convocatories;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
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
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public OffererState getOffererState() {
		return offererState;
	}

	public void setOffererState(OffererState offererState) {
		this.offererState = offererState;
	}

	
}


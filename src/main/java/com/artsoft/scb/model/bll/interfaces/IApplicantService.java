package com.artsoft.scb.model.bll.interfaces;

import com.artsoft.scb.model.entity.Applicant;

public interface IApplicantService {
	
	/**
	 * Crea el solicitante
	 * @param applicant el solicitante.
	 * @throws Exception : lanza excepcion si alguna propiedad del solicitante no es valida.
	 */
	boolean createApplicant(Applicant applicant) throws Exception;
}

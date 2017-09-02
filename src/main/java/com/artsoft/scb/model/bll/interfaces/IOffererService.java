package com.artsoft.scb.model.bll.interfaces;

import com.artsoft.scb.model.entity.Offerer;

public interface IOffererService {
	/**
	 * Crea el oferente
	 * @param oferent el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	boolean createOferent(Offerer oferent) throws Exception;
}

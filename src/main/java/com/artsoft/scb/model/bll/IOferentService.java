package com.artsoft.scb.model.bll;

import com.artsoft.scb.model.entity.Oferent;

public interface IOferentService {
	/**
	 * Crea el oferente
	 * @param oferent el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	boolean createOferent(Oferent oferent) throws Exception;
}

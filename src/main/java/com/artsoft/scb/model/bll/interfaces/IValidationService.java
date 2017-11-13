package com.artsoft.scb.model.bll.interfaces;

import com.artsoft.scb.model.entity.Field;
import com.artsoft.scb.model.entity.Validation;

public interface IValidationService {

	boolean linkField(Validation validation, Field field) throws Exception;
}

package com.artsoft.scb.model.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IValidationService;
import com.artsoft.scb.model.dao.ValidationRepository;
import com.artsoft.scb.model.entity.Field;
import com.artsoft.scb.model.entity.Validation;

@Service
public class ValidationService extends ExceptionService implements IValidationService {

	@Autowired
	private ValidationRepository validationRepository;
	
	@Override
	public boolean linkField(Validation validation, Field field) throws Exception {
		if(validation != null){
		validation.setField(field);
		Validation validationSaved = validationRepository.save(validation);
		if (validationSaved == null) {
			return false;			
		}
		}
		return true;
	}

}

package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IConvocatoryTypeService;
import com.artsoft.scb.model.dao.ConvocatoryTypeRepository;
import com.artsoft.scb.model.entity.ConvocatoryType;

@Service
public class ConvocatoryTypeService implements IConvocatoryTypeService {

	/**
	 * Repository of document type.
	 */
	@Autowired
	private ConvocatoryTypeRepository convocatoryTypeRepository;
	
	public List<ConvocatoryType> get() {
		return (List<ConvocatoryType>) convocatoryTypeRepository.findAll();
	}

}

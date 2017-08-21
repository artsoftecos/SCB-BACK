package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IDocumentTypeService;
import com.artsoft.scb.model.dao.DocumentTypeRepository;
import com.artsoft.scb.model.entity.DocumentType;

@Service
public class DocumentTypeService implements IDocumentTypeService {
	
	/**
	 * Repository of document type.
	 */
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	public List<DocumentType> get() {
		return (List<DocumentType>) documentTypeRepository.findAll();
	}
}



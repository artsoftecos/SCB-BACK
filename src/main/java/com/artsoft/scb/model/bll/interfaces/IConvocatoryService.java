package com.artsoft.scb.model.bll.interfaces;

import java.util.List;

import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;

public interface IConvocatoryService {
	
	boolean createConvocatory(Convocatory convocatory) throws Exception;
	
	Convocatory getByOffer(String mailOffer) throws Exception; 
	
	Convocatory getById(int id) throws Exception;
	
	List<Convocatory> getByState(ConvocatoryState convState) throws Exception;

	List<Convocatory> getByPendingPhases() throws Exception;
	
}

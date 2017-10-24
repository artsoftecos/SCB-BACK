package com.artsoft.scb.model.bll.interfaces;

import java.util.List;

import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;

public interface IConvocatoryService {
	
	boolean createConvocatory(Convocatory convocatory) throws Exception;
	
	List<Convocatory> getByOffer(String mailOffer) throws Exception; 
	
	Convocatory getById(int id) throws Exception;
	
	List<Convocatory> getByState(ConvocatoryState convState) throws Exception;

	List<Convocatory> getByPendingPhases(String mailOfferer) throws Exception;

	
	boolean editConvocatory(Convocatory convocatory) throws Exception;
	

	List<Convocatory> getByOffererState(String mailOfferer, int state) throws Exception;
}

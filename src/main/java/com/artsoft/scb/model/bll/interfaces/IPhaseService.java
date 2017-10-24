package com.artsoft.scb.model.bll.interfaces;

import com.artsoft.scb.model.entity.Phase;

public interface IPhaseService {
	
	boolean createPhase(Phase phase) throws Exception;
	boolean editPhase(Phase phase) throws Exception;
	Phase getPhaseById(int idPhase) throws Exception;

}

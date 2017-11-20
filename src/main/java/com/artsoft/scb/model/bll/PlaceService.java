package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PlaceRepository;
import com.artsoft.scb.model.dao.PlaceStateRepository;
import com.artsoft.scb.model.dao.RejectPlaceStructure;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Place;
import com.artsoft.scb.model.entity.PlaceState;

@Service
public class PlaceService extends ExceptionService {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private PlaceStateRepository placeStateRepository;
	
	@Autowired
	private ConvocatoryRepository convocatoryRepository;
	
	private final int ID_PENDIENTE = 1;
	private final int ID_ACEPTADA = 2;
	private final int ID_RECHAZADA = 3;
	
	public void createPlace(Place place){
		PlaceState placeState = placeStateRepository.findById(ID_PENDIENTE);
		place.setPlaceState(placeState);
		
		placeRepository.save(place);
	}
	
	
	public List<Place> getPlaceByEmail(String applicantEmail){
		Applicant applicant = applicantRepository.findByEmail(applicantEmail);
		return placeRepository.findByApplicant(applicant);
	}
	
	public boolean acceptPlace(int idPlace) throws Exception{
		validatePlace(idPlace);
		Place placeToEdit = placeRepository.findById(idPlace);		
		PlaceState placeState = placeStateRepository.findById(ID_ACEPTADA);
		placeToEdit.setPlaceState(placeState);
		
		Place placeSaved = placeRepository.save(placeToEdit);
		
		if(placeSaved == null){
			return false;
		}
		
		return true;
	}
	
	public boolean rejectPlace(RejectPlaceStructure rejectPlaceStructure) throws Exception{
		validatePlace(rejectPlaceStructure.getIdPlace());
		Place placeToEdit = placeRepository.findById(rejectPlaceStructure.getIdPlace());
		PlaceState placeState = placeStateRepository.findById(ID_RECHAZADA);
		placeToEdit.setPlaceState(placeState);
		placeToEdit.setRejectionCause(rejectPlaceStructure.getRejectCause());
		
		Place placeSaved = placeRepository.save(placeToEdit);
		
		if(placeSaved == null){
			return false;
		}
		
		return true;
	}
	
	private void validatePlace(int idPlace) throws Exception{
		Place placeFound = placeRepository.findById(idPlace);
		
		if(placeFound == null){
			throwException("idPlace", "No existe una plaza asociada ese ID");
		}
	}
	
}

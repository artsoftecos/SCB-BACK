package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PlaceRepository;
import com.artsoft.scb.model.dao.PlaceStateRepository;
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
	
	public List<Place> getPlaceByEmail(String applicantEmail){
		Applicant applicant = applicantRepository.findByEmail(applicantEmail);
		return placeRepository.findByApplicant(applicant);
	}
	
	public boolean acceptPlace(int idConvocatory, String mailApplicant) throws Exception{
		validateMail(mailApplicant);
		validateConvocatory(idConvocatory);
		Applicant applicant = applicantRepository.findByEmail(mailApplicant);
		List<Place> places = placeRepository.findByApplicant(applicant);
		Place placeToEdit = new Place();
		for (int i = 0; i < places.size(); i++) {
			if(places.get(i).getConvocatory().getId() == idConvocatory){
				placeToEdit = placeRepository.findByConvocatory(places.get(i).getConvocatory());
				break;
			}
			
		}
		PlaceState placeState = placeStateRepository.findById(ID_ACEPTADA);
		placeToEdit.setPlaceState(placeState);
		
		Place placeSaved = placeRepository.save(placeToEdit);
		
		if(placeSaved == null){
			return false;
		}
		
		return true;
	}
	
	private void validateMail(String mail) throws Exception{
		Applicant applicantFound = applicantRepository.findByEmail(mail);
		
		if(applicantFound == null){
			throwException("email", "No existe un solicitante asociado a ese email");
		}
	}
	
	private void validateConvocatory(int idConvocatory) throws Exception{
		Convocatory convocatoryFound = convocatoryRepository.findById(idConvocatory);
		
		if(convocatoryFound == null){
			throwException("id", "No existe una convocatoria asociada a ese id");
		}
	}

}

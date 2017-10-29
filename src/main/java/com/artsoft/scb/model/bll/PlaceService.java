package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.PlaceRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Place;

@Service
public class PlaceService {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	public List<Place> getPlaceByEmail(String applicantEmail){
		Applicant applicant = applicantRepository.findByEmail(applicantEmail);
		return placeRepository.findByApplicant(applicant);
	}

}

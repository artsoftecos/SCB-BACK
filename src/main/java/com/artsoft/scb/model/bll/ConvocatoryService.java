package com.artsoft.scb.model.bll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IConvocatoryService;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.ConvocatoryStateRepository;
import com.artsoft.scb.model.dao.ConvocatoryTypeRepository;
import com.artsoft.scb.model.dao.OffererRepository;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.ConvocatoryState;
import com.artsoft.scb.model.entity.ConvocatoryType;
import com.artsoft.scb.model.entity.Offerer;
import com.artsoft.scb.model.entity.Phase;
import com.artsoft.scb.model.entity.Place;
import com.artsoft.scb.model.entity.Postulation;


@Service
public class ConvocatoryService extends ExceptionService implements IConvocatoryService{

	@Autowired
	private ConvocatoryStateRepository convocatoryStateRepository;
	
	@Autowired
	private ConvocatoryTypeRepository convocatoryTypeRepository;
	
	@Autowired
	private ConvocatoryRepository convocatoryRepository;
	
	@Autowired
	private OffererRepository offererRepository;
	
	@Autowired
	private PhaseService phaseService;
	
	@Autowired 
	private PlaceService placeService;
	
	@Autowired
	private PostulationService postulationService;
	
	
	private final int ID_CREADA = 1;
	private final int ID_CERRADA = 2;
	private final int ID_ABIERTA = 3;
	private final int ID_PUBLICADA = 4;
	
	@Override
	public boolean createConvocatory(Convocatory convocatory) throws Exception {
		validateConvocatory(convocatory,1);
		convocatory.setConvocatoryState(convocatoryStateRepository.findById(ID_CREADA));
		Offerer offerer = offererRepository.findByEmail(convocatory.getOfferer().getEmail());
		convocatory.setOfferer(offerer);
		Convocatory convocatorySaved = convocatoryRepository.save(convocatory);		
		if(convocatorySaved == null){
			return false;
		}
		
		return true;
	}
	
	private void validateConvocatory(Convocatory convocatory, int validationType) throws Exception{
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(convocatory);
		
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations){
				parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());				
			}			
		}
		
		if (parameters.values().size() > 0){
			throwException(parameters);
		}
		
		if(validationType == 1){
			validateMail(convocatory.getOfferer().getEmail());
		}
		
		validateConvocatoryType(convocatory.getConvocatoryType().getId());
		validateNumberOfBeneficiaries(convocatory);
		validateEmptyName(convocatory);
		validateEmptyDescription(convocatory);
	}
	
	private void validateMail(String email) throws Exception{
		Offerer offererFound = offererRepository.findByEmail(email);
		
		if(offererFound == null){
			throwException("email", "No existe un oferente asociado a ese correo");
		}
	}
	
	private void validateConvocatoryType(int id) throws Exception{
		ConvocatoryType convocatoryTypeFound = convocatoryTypeRepository.findById(id);
		
		if(convocatoryTypeFound == null){
			throwException("convocatoryType", "El tipo de convocatoria ingresado no existe");
		}
	}
	
	private void validateNumberOfBeneficiaries(Convocatory convocatory) throws Exception{
		if(convocatory.getNumberBeneficiaries() <= 0){
			throwException("numberBeneficiaries", "El número de beneficiarios es inválido, debe ser mayor a cero");
		}
	}
	
	private void validateEmptyName(Convocatory convocatory) throws Exception{
		if(convocatory.getName().isEmpty() || convocatory.getName() == null){
			throwException("name", "El nombre es obligatorio");
		}
	}
	
	private void validateEmptyDescription(Convocatory convocatory) throws Exception{
		if(convocatory.getDescription().isEmpty() || convocatory.getDescription() == null){
			throwException("description", "La descripción es obligatoria");
		}
	}

	@Override
	public List<Convocatory> getByOffer(String mailOffer) throws Exception {
		Offerer of = new Offerer();
		of.setEmail(mailOffer);
		return convocatoryRepository.findByOfferer(of);
	}

	@Override
	public Convocatory getById(int id) throws Exception {
		return convocatoryRepository.findById(id);
	}

	@Override
	public List<Convocatory> getByState(ConvocatoryState convState) throws Exception {
		return convocatoryRepository.findByConvocatoryState(convState);
	}

	@Override
	public List<Convocatory> getByPendingPhases(String mailOfferer) throws Exception {
		ConvocatoryState convState = new ConvocatoryState();
		convState.setId(1);
		convState.setName("pendiente");
		convocatoryRepository.findByConvocatoryState(convState);
		return convocatoryRepository.findByConvocatoryState(convState);
	}

	@Override
	public List<Convocatory> getByOffererState(String mailOfferer, int state) throws Exception {
		Offerer of = new Offerer();
		of.setEmail(mailOfferer);
		List<Convocatory> convocatories = convocatoryRepository.findByOfferer(of);
		List<Convocatory> finalConvocatories = new ArrayList<Convocatory>();
		for (Convocatory convocatory : convocatories) {
			if(convocatory.getConvocatoryState().getId() == state)
				finalConvocatories.add(convocatory);		
		}
		return finalConvocatories;
	}

	@Override
	public boolean editConvocatory(Convocatory convocatory) throws Exception {
		Convocatory convocatoryToEdit = convocatoryRepository.findById(convocatory.getId());
		validateConvocatory(convocatoryToEdit, 2);
		convocatoryToEdit.setName(convocatory.getName());
		convocatoryToEdit.setDescription(convocatory.getDescription());
		convocatoryToEdit.setNumberBeneficiaries(convocatory.getNumberBeneficiaries());
		convocatoryToEdit.setConvocatoryType(convocatory.getConvocatoryType());
		convocatoryToEdit.setResultDate(convocatory.getResultDate());
		Convocatory convocatorySaved = convocatoryRepository.save(convocatoryToEdit);
		if(convocatorySaved == null){
			return false;
		}
		return true;
	}
	
	
	public List<Convocatory> getAllConvocatories(){
		List<Convocatory> convocatories = (List<Convocatory>) convocatoryRepository.findAll();
		return convocatories;
	}
	
	public List<Convocatory> getConvocatoriesWithPhasesToApprove(String mailOfferer){
		Offerer of = new Offerer();
		of.setEmail(mailOfferer);
		List<Convocatory> convocatories = (List<Convocatory>) convocatoryRepository.findByOfferer(of);
		List<Convocatory> convocatoriesToReturn = new ArrayList<Convocatory>();
		for (int i = 0; i < convocatories.size(); i++) {
			Set<Phase> setPhasesTemp = convocatories.get(i).getPhases();
			List<Phase> phasesTemp = new ArrayList<Phase>(setPhasesTemp);
			List<Phase> validPhases = phaseService.getPhasesWithApplicantsToApprove(phasesTemp);
			if(validPhases.size() > 0){
				convocatoriesToReturn.add(convocatories.get(i));
			}			
		}
		return convocatoriesToReturn;
	}
	/**
	 * 
	 * @param mailApplicant Mail del solicitante
	 * @return Listado de convocatorias a las que ha aplicado un solicitante pero no ha sido aprobado 
	 */
	public List<Convocatory> getConvocatoriesOfApplicant(String mailApplicant){
		List<Place> placesOfApplicant = placeService.getPlaceByEmail(mailApplicant);
		List<Postulation> postulationsOfApplicant = postulationService.getPostulationsByEmail(mailApplicant);
		List<Convocatory> convocatories = new ArrayList<Convocatory>();
		for (int i = 0; i < postulationsOfApplicant.size(); i++) {
			try {
				convocatories.add(getById(postulationsOfApplicant.get(i).getConvocatory().getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(placesOfApplicant.isEmpty()){			
			return convocatories;
		}else{
			for (int i = 0; i < placesOfApplicant.size(); i++) {
				for (int j = 0; j < convocatories.size(); j++) {
					if (placesOfApplicant.get(i).getConvocatory().getId() == convocatories.get(j).getId()) {
						convocatories.remove(j);
					}
					
				}
			}
		}
		return convocatories;
	}
	
	public List<Place> getConvocatoriesOfApplicantWithPlaces(String mailApplicant){
		List<Place> placesOfApplicant = placeService.getPlaceByEmail(mailApplicant);
		/*List<Convocatory> convocatories = new ArrayList<Convocatory>();
		for (int i = 0; i < placesOfApplicant.size(); i++) {
			try {
				convocatories.add(getById(placesOfApplicant.get(i).getConvocatory().getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return convocatories;*/
		return placesOfApplicant;
	}
	
	public List<Convocatory> getNotAppliedConvocatories(String mailApplicant){
		List<Place> placesOfApplicant = placeService.getPlaceByEmail(mailApplicant);
		List<Postulation> postulationsOfApplicant = postulationService.getPostulationsByEmail(mailApplicant);
		List<Convocatory> convocatories = getAllConvocatories();
		if(placesOfApplicant.isEmpty() && postulationsOfApplicant.isEmpty()){
			return getConvocatoriesWithPhases(convocatories);
		}
		for (int i = 0; i < placesOfApplicant.size(); i++) {
			for (int j = 0; j < convocatories.size(); j++) {
				if (placesOfApplicant.get(i).getConvocatory().getId() == convocatories.get(j).getId()) {
					convocatories.remove(j);
				}
				
			}
		}
		
		for (int i = 0; i < postulationsOfApplicant.size(); i++) {
			for (int j = 0; j < convocatories.size(); j++) {
				if (postulationsOfApplicant.get(i).getConvocatory().getId() == convocatories.get(j).getId()) {
					convocatories.remove(j);
				}
				
			}
		}
		
		return getConvocatoriesWithPhases(convocatories);
	}
	
	
	private List<Convocatory> getConvocatoriesWithPhases(List<Convocatory> convocatories){
		Date fechaHoy = new Date(System.currentTimeMillis());
		List<Convocatory> convocatoriesTemp = new ArrayList<Convocatory>();
		List<Convocatory> convocatoriesToReturn = new ArrayList<Convocatory>();
		convocatoriesTemp = convocatories;
		boolean devolverConvocatoria;
		for (int i = 0; i < convocatoriesTemp.size(); i++) {
			devolverConvocatoria = true;
			Convocatory convTemp = convocatoriesTemp.get(i);
			List<Phase> phasesTemp = new ArrayList<Phase>();
			phasesTemp.addAll(convTemp.getPhases());
			int indiceFechaMenor = 0;
			
			if(phasesTemp.isEmpty()){
				devolverConvocatoria = false;
			}else{
				for (int j = 1; j < phasesTemp.size(); j++) {
					if(phasesTemp.get(j).getStartDate().getTime() < phasesTemp.get(indiceFechaMenor).getStartDate().getTime()){
						indiceFechaMenor = j;
					}
				}
				
				if(phasesTemp.get(indiceFechaMenor).getEndDate().before(fechaHoy) && fechaHoy.after(phasesTemp.get(indiceFechaMenor).getStartDate())){
					devolverConvocatoria = false;
				}
			}
			
			
			if(devolverConvocatoria == true){
				convocatoriesToReturn.add(convTemp);
				devolverConvocatoria = false;
			}
				
		}
		
		return convocatoriesToReturn;
	}
}

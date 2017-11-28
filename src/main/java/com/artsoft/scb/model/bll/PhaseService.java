package com.artsoft.scb.model.bll;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IPhaseService;
import com.artsoft.scb.model.dao.ApplicantPerPhaseStateRepository;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PhaseRepository;
import com.artsoft.scb.model.dao.PlaceRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantPerPhase;
import com.artsoft.scb.model.entity.ApplicantPerPhaseState;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Phase;
import com.artsoft.scb.model.entity.Place;
import com.artsoft.scb.model.entity.Postulation;
import com.artsoft.scb.model.entity.StatePhaseAndAplicant;

@Service
public class PhaseService extends ExceptionService implements IPhaseService {

	@Autowired
	private PhaseRepository phaseRepository;
		
	@Autowired
	private ConvocatoryRepository convocatoryRepository;
	
	@Autowired
	private ApplicantPerPhaseService applicantPerPhaseService;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private PostulationService postulationService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private HelperService helperService;
	
	@Value("${Email.SubjectResultPhase}")
	private String subjectResultPhase;
	
	@Value("${Email.PhaseApprovedEmail}")
	private String pathPhaseApprovedEmail;
	
	@Value("${Email.PhaseRejectedEmail}")
	private String pathPhaseRejectedEmail;
	
	@Value("${Email.PlaceAcceptedEmail}")
	private String pahtPlaceAcceptedEmail;
	
	@Value("${Email.SubjectDecision}")
	private String subjectDecision;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public boolean createPhase(Phase phase) throws Exception {
		System.out.println("Antes de validar: " + phase.getStartDate());
		validatePhase(phase,1);
		Phase phaseSaved = phaseRepository.save(phase);
		if (phaseSaved == null) {
			return false;			
		}
		return true;
	}
	
	public void deletePhase(int idPhase) throws Exception{
		phaseRepository.delete(idPhase);		
	}

	private void validatePhase(Phase phase, int tipoValidacion) throws Exception{
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(phase);
		
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations){
				parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());				
			}			
		}
		
		if (parameters.values().size() > 0){
			throwException(parameters);
		}
		validateEmptyName(phase);
		validateEmptyDescription(phase);
		validateStartDate(phase);
		validateEndDate(phase);
		validateStartApprovalDate(phase);
		if(tipoValidacion == 1){
			validateConvocatory(phase);
		}		
	}
	
	private void validateEmptyName(Phase phase) throws Exception{
		if(phase.getName().isEmpty() || phase.getName() == null){
			throwException("name", "El nombre es obligatorio");
		}
	}
	
	private void validateEmptyDescription(Phase phase) throws Exception{
		if(phase.getDescription().isEmpty() || phase.getDescription() == null){
			throwException("description", "La descripción es obligatoria");
		}
	}
	
	private void validateStartDate(Phase phase) throws Exception{
		Timestamp today = new Timestamp(System.currentTimeMillis());
		System.out.println("hoy: " + today);
		System.out.println("entra: " + phase.getStartDate());
		if (phase.getStartDate().getTime() < today.getTime()) {
			throwException("startDate", "La fecha de inicio no puede ser anterior a la fecha actual");
		}
		
		if (phase.getStartDate() == null) {
			throwException("startDate", "La fecha de inicio es obligatoria");
		}
	}
	
	private void validateEndDate(Phase phase) throws Exception{
		Timestamp today = new Timestamp(System.currentTimeMillis());
		if (phase.getEndDate().before(today) || phase.getEndDate().before(phase.getStartDate())) {
			throwException("endDate", "La fecha de finalización no puede ser anterior a la fecha de hoy, ni anterior a la fecha de inicio");
		}
		
		if (phase.getEndDate() == null) {
			throwException("endDate", "La fecha de finalización es obligatoria");
		}
	}
	
	private void validateStartApprovalDate(Phase phase) throws Exception{
		Timestamp today = new Timestamp(System.currentTimeMillis());
		if (phase.getStartApprovalDate().before(today) || phase.getStartApprovalDate().before(phase.getStartDate()) || phase.getStartApprovalDate().before(phase.getEndDate())) {
			throwException("startApprovalDate", "La fecha de inicio de aprobación no puede ser anterior a la fecha de hoy o fecha de inicio o a fecha de finalización");
		}
		
		if (phase.getEndDate() == null) {
			throwException("endDate", "La fecha de inicio de aprobación es obligatoria");
		}
	}
	
	private void validateConvocatory(Phase phase) throws Exception{
		Convocatory convocatoryFound = convocatoryRepository.findById(phase.getConvocatory().getId());
		if (convocatoryFound == null) {
			throwException("id","La convocatoria ingresada no existe");
		}
	}



	@Override
	public boolean editPhase(Phase phase) throws Exception {
		Phase phaseToEdit = phaseRepository.findById(phase.getId());
		validatePhase(phase, 2);
		phaseToEdit.setName(phase.getName());
		phaseToEdit.setDescription(phase.getDescription());
		phaseToEdit.setStartDate(phase.getStartDate());
		phaseToEdit.setEndDate(phase.getEndDate());
		phaseToEdit.setStartApprovalDate(phase.getStartApprovalDate());
		phaseToEdit.setEndApprovalDate(phase.getEndApprovalDate());
		Phase phaseSaved = phaseRepository.save(phaseToEdit);
		if (phaseSaved == null) {
			return false;			
		}
		return true;
	}
	
	public List<Phase> getPhaseByConvocatoryId(int idConvocatory){
		Convocatory convocatory = convocatoryRepository.findById(idConvocatory);
		return phaseRepository.findByConvocatory(convocatory);
	}

	@Override
	public Phase getPhaseById(int idPhase) throws Exception {
		return phaseRepository.findById(idPhase);
	}
	
	private List<Phase> getPhasesBetweenDates(List<Phase> phases){
		List<Phase> phasesSelected = new ArrayList<Phase>();
		Date today = new Date(System.currentTimeMillis());
		for (int i = 0; i < phases.size(); i++) {
			if(today.after(phases.get(i).getStartApprovalDate()) && today.before(phases.get(i).getEndApprovalDate())){
				phasesSelected.add(phases.get(i));
			}
		}
		return phasesSelected;
	}

	private List<Phase> getPhasesFiltered(List<Phase> phasesNonFiltered){
		List<ApplicantPerPhase> applicantsPending = applicantPerPhaseService.getApplicantPerPhaseByState(2);
		List<Phase> phasesToReturn = new ArrayList<Phase>();
		for (int i = 0; i < applicantsPending.size(); i++) {
			for (int j = 0; j < phasesNonFiltered.size(); j++) {
				if(phasesNonFiltered.get(j).getId() == applicantsPending.get(i).getPhase().getId()){
					phasesToReturn.add(phasesNonFiltered.get(j));
				}
			}
		}
		return phasesToReturn;
		
	}
	public List<Phase> getPhasesWithApplicantsToApprove(List<Phase> phasesPerConvocatory){
		/*
		List<ApplicantPerPhase> applicantsPending = applicantPerPhaseService.getApplicantPerPhaseByState(2);
		List<Phase> phasesWithApplicantsApproved = new ArrayList<Phase>();
		for (int i = 0; i < applicantsPending.size(); i++) {
			phasesWithApplicantsApproved.add(applicantsPending.get(i).getPhase());
		}*/
		List<Phase> phasesToReturn = getPhasesBetweenDates(getPhasesFiltered(phasesPerConvocatory));
		return phasesToReturn;
	}
	
	public StatePhaseAndAplicant getCurrentPhaseForConvocatory(int idConvocatory) throws Exception {
		
		StatePhaseAndAplicant statePhaseAndAplicant = new StatePhaseAndAplicant();
		statePhaseAndAplicant.setPhase(null);
		statePhaseAndAplicant.setState(null);
		
		Convocatory convocatory =  convocatoryRepository.findById(idConvocatory);
		List<Phase> listPhasesConvocatory = phaseRepository.findByConvocatory(convocatory);
		if (listPhasesConvocatory.size() == 0){
			throwException("summary", "No hay fases en esta convocatoria");			
		}
		
		//1. Para esa convocatoria obtiene la fase actual por las fechas de Inicio de fase a Fecha finalizacion de aprobacion de oferente
		Phase currentPhase = getCurrentPhaseByInitDateAndFinishApprovedOfferer(listPhasesConvocatory);
		if (currentPhase == null){
			//null phase and state
			return statePhaseAndAplicant;
		}
		
		statePhaseAndAplicant.setPhase(currentPhase);	
		return statePhaseAndAplicant;
	}
	
	public StatePhaseAndAplicant getCurrentPhaseForConvocatoryAndApplicant(int idConvocatory, String mailApplicant) throws Exception {
				
		StatePhaseAndAplicant statePhaseAndAplicant = new StatePhaseAndAplicant();
		statePhaseAndAplicant.setPhase(null);
		statePhaseAndAplicant.setState(null);
		
		Convocatory convocatory =  convocatoryRepository.findById(idConvocatory);
		List<Phase> listPhasesConvocatory = phaseRepository.findByConvocatory(convocatory);
		if (listPhasesConvocatory.size() == 0){
			throwException("summary", "No hay fases en esta convocatoria");			
		}
		
		//1. Para esa convocatoria obtiene la fase actual por las fechas de Inicio de fase a Fecha finalizacion de aprobacion de oferente
		Phase currentPhase = getCurrentPhaseByInitDateAndFinishApprovedOfferer(listPhasesConvocatory);
		if (currentPhase == null){
			//null phase and state
			return statePhaseAndAplicant;
		}
		
		statePhaseAndAplicant.setPhase(currentPhase);
		
		//para la fase actual, consulte si ya aplico y retorne el estado actual.
		ApplicantPerPhase applicantPerPhase = getApplicantByPhase(currentPhase, mailApplicant);
		if (applicantPerPhase != null) {
			String currentState = applicantPerPhase.getApplicantPerPhaseState().getName();
			DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");			
			java.util.Date today = inputFormatter.parse(DateTime.now().toString());
			java.util.Date endDate = inputFormatter.parse(currentPhase.getEndDate().toString());			
			if (currentState.equals("PendienteRegistroDatos") && today.getTime() >  endDate.getTime())
			{		
				applicantPerPhaseService.rejectApplicantPerPhase(applicantPerPhase);				
				currentState = "Rechazado";				
			}
			statePhaseAndAplicant.setState(currentState);			
			return statePhaseAndAplicant;
		}
		
		//No esta en la fase actual, pueden ser 2 cosas: nunca a aplicado o lo rechazaron de fase anterior.
		List<ApplicantPerPhase> list = getApplicantPerPhaseInAllPhases(listPhasesConvocatory, mailApplicant);
		if (list.isEmpty()){
			statePhaseAndAplicant.setState("PendienteRegistroDatos");			
			return statePhaseAndAplicant;
		}
		
		statePhaseAndAplicant.setState("RechazadoFaseAnterior");		
		return statePhaseAndAplicant;
	}
	
	private Phase getCurrentPhaseByInitDateAndFinishApprovedOfferer(List<Phase> listPhasesConvocatory) throws ParseException{		
		for (Phase phase: listPhasesConvocatory) {
			DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date startDate = inputFormatter.parse(phase.getStartDate().toString());
			java.util.Date finishApprovalDate = inputFormatter.parse(phase.getEndApprovalDate().toString());
			java.util.Date today = inputFormatter.parse(DateTime.now().toString());			
			if(today.getTime() >= startDate.getTime() && today.getTime() <= finishApprovalDate.getTime()){
				return phase;
			}
		}
		return null;				
	}
	
	private ApplicantPerPhase getApplicantByPhase(Phase currentPhase, String mailApplicant) throws JSONException {
		Applicant applicant = applicantRepository.findByEmail(mailApplicant);
		if (applicant == null){
			throwException("summary", "No se encontro al usuario.");	
		}
		
		ApplicantPerPhase applicantPerPhase = applicantPerPhaseService.getApplicantPerPhaseByApplicantAndPhase(applicant, currentPhase);
		
		return applicantPerPhase;		
	}
	
	private List<ApplicantPerPhase> getApplicantPerPhaseInAllPhases(List<Phase> listPhases, String mailApplicant){
		List<ApplicantPerPhase> list = new ArrayList<ApplicantPerPhase>();
		Applicant applicant = applicantRepository.findByEmail(mailApplicant);
		
		for (Phase phase: listPhases) {
			ApplicantPerPhase applicantPerPhase = applicantPerPhaseService.getApplicantPerPhaseByApplicantAndPhase(applicant, phase);
			if (applicantPerPhase != null) {
				list.add(applicantPerPhase);
			}
		}	
		return list;
	}
	
	private boolean isFirstPhase(Phase phase){
		Convocatory convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
		List<Phase> phases = phaseRepository.findByConvocatory(convocatory);
		Phase firstPhase = new Phase();
		firstPhase = phases.get(0);
		
		for(int i = 1; i < phases.size() ; i++){
			if(phases.get(i).getStartDate().before(firstPhase.getStartDate())){
				firstPhase = phases.get(i);
			}
		}
		
		if(phase.getId() == firstPhase.getId()){
			return true;
		}
		
		return false;
	}
	
	private boolean isLastPhase(Phase phase){
		Convocatory convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
		List<Phase> phases = phaseRepository.findByConvocatory(convocatory);
		Phase lastPhase = new Phase();
		lastPhase = phases.get(0);
		
		for(int i = 1; i < phases.size() ; i++){
			if(phases.get(i).getStartDate().after(lastPhase.getStartDate())){
				lastPhase = phases.get(i);
			}
		}
		
		if(phase.getId() == lastPhase.getId()){
			return true;
		}
		
		return false;
	}
	
	public void managePhaseApplication(ApplicantPerPhase applicantPerPhase) throws Exception{
		Phase phase = new Phase();
		phase = applicantPerPhase.getPhase();
		
		if(isFirstPhase(phase)){
			createPostulation(applicantPerPhase);
		}
	}
	
	public void manageApprovedPhases(ApplicantPerPhase applicantPerPhase) throws Exception{
		Phase phase = new Phase();
		phase = applicantPerPhase.getPhase();
		
		Applicant applicant = new Applicant();
		applicant = applicantRepository.findByEmail(applicantPerPhase.getApplicant().getEmail());
		Convocatory convocatory = new Convocatory();
		convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
		
		if(isLastPhase(phase)){
			createPlace(applicantPerPhase);
			sendPlaceCreatedEmail(applicant.getFirstName(), convocatory.getName(), applicant.getEmail());
			
		}else {
			asociateApplicantToNextPhase(phase, applicant);
		}
	}
	private void createPostulation(ApplicantPerPhase applicantPerPhase) throws Exception{
		Phase phase = new Phase();
		phase = applicantPerPhase.getPhase();
		Applicant applicant = new Applicant();
		applicant = applicantRepository.findByEmail(applicantPerPhase.getApplicant().getEmail());
		Convocatory convocatory = new Convocatory();
		convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
		
		Date today = new Date(System.currentTimeMillis());
		
		Postulation postulation = new Postulation();
		postulation.setApplicant(applicant);
		postulation.setConvocatory(convocatory);
		postulation.setPostulationDate(today);
		postulationService.createPostulation(postulation);		
	}
	
	private void createPlace(ApplicantPerPhase applicantPerPhase){
		Phase phase = new Phase();
		phase = applicantPerPhase.getPhase();
		Applicant applicant = new Applicant();
		applicant = applicantRepository.findByEmail(applicantPerPhase.getApplicant().getEmail());
		Convocatory convocatory = new Convocatory();
		convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
	
		Place place = new Place();
		place.setApplicant(applicant);
		place.setConvocatory(convocatory);
		placeService.createPlace(place);
		
	}
	
	private void asociateApplicantToNextPhase(Phase phase, Applicant applicant) throws Exception{
		Convocatory convocatory = new Convocatory();
		convocatory = convocatoryRepository.findById(phase.getConvocatory().getId());
		
		List<Phase> phasesSorted = new ArrayList<Phase>(convocatory.getPhases());
		
		//List<Phase> phasesSorted = new ArrayList<Phase>();
		
		Collections.sort(phasesSorted);
		int nextPhaseIndex = 0;
		
		for (int i = 0; i < phasesSorted.size(); i++) {
			if(phasesSorted.get(i).getId() == phase.getId()){
				nextPhaseIndex = i+1;
			}
		}
		
		ApplicantPerPhase applicantPerPhase = new ApplicantPerPhase();
		applicantPerPhase.setApplicant(applicant);
		applicantPerPhase.setPhase(phasesSorted.get(nextPhaseIndex));
		applicantPerPhaseService.asociateApplicantToAPhase(applicantPerPhase);
		sendApprovedEmail(applicant.getFirstName(), phasesSorted.get(nextPhaseIndex - 1).getName(), convocatory.getName(), phasesSorted.get(nextPhaseIndex).getName(), applicant.getEmail());
		
	}
	
	
	private void sendApprovedEmail(String applicantName, String phaseName, String convName, String netxPhaseName, String mailApplicant) throws Exception{
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		
		parameters.put("[NAME]", applicantName);
		parameters.put("[PHASE_NAME]", phaseName);
		parameters.put("[CONV_NAME]", convName);
		parameters.put("[NEXT_PHASE_NAME]", netxPhaseName);
		
		String bodyEmailToSend = helperService.getEmail(pathPhaseApprovedEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(mailApplicant);		
		
		messageService.sendMessage(bodyEmailToSend, destinies, subjectResultPhase);
	
		
	}
	
	public void sendRejectedEmail(String applicantName, String phaseName, String convName, String mailApplicant) throws Exception{
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		
		parameters.put("[NAME]", applicantName);
		parameters.put("[PHASE_NAME]", phaseName);
		parameters.put("[CONV_NAME]", convName);
				
		String bodyEmailToSend = helperService.getEmail(pathPhaseRejectedEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(mailApplicant);		
		
		messageService.sendMessage(bodyEmailToSend, destinies, subjectResultPhase);
	
		
	}
	
	public void sendPlaceCreatedEmail(String applicantName, String convName, String mailApplicant) throws Exception{
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		
		parameters.put("[NAME]", applicantName);
		parameters.put("[CONV_NAME]", convName);
				
		String bodyEmailToSend = helperService.getEmail(pahtPlaceAcceptedEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(mailApplicant);		
		
		messageService.sendMessage(bodyEmailToSend, destinies, subjectDecision);
	
		
	}
}


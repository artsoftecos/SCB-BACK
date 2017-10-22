package com.artsoft.scb.model.bll;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IPhaseService;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PhaseRepository;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Phase;
@Service
public class PhaseService extends ExceptionService implements IPhaseService {

	@Autowired
	private PhaseRepository phaseRepository;
	
	@Autowired
	private ConvocatoryRepository convocatoryRepository;
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
		validatePhase(phaseToEdit, 2);
		phaseToEdit.setName(phase.getName());
		phaseToEdit.setDescription(phase.getDescription());
		phaseToEdit.setStartDate(phase.getStartDate());
		phaseToEdit.setEndDate(phase.getEndDate());
		phaseToEdit.setStartApprovalDate(phase.getStartApprovalDate());
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
	
	
}

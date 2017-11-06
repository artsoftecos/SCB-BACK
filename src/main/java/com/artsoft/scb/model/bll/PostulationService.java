package com.artsoft.scb.model.bll;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IPostulationService;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PostulationRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Postulation;

@Service
public class PostulationService extends ExceptionService implements IPostulationService{
	
	@Autowired
	private PostulationRepository postulationRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private ConvocatoryRepository convocatoryRepository;
	
	public List<Postulation> getPostulationsByEmail(String applicantMail){
		Applicant applicant = applicantRepository.findByEmail(applicantMail);
		return postulationRepository.findByApplicant(applicant);
	}
	
	@Override
	public boolean createPostulation(Postulation postulation) throws Exception {
		validatePostulation(postulation);
		Applicant applicant = applicantRepository.findByEmail(postulation.getApplicant().getEmail());
		Convocatory convocatory = convocatoryRepository.findById(postulation.getConvocatory().getId());
		postulation.setPostulationDate(new Date(System.currentTimeMillis()));
		postulation.setApplicant(applicant);
		postulation.setConvocatory(convocatory);
		Postulation postulationSaved = postulationRepository.save(postulation);
		if(postulationSaved == null){
			return false;
		}
		
		return true;
	}
	
	private void validatePostulation(Postulation postulation) throws Exception{
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(postulation);
		
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations){
				parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());				
			}			
		}
		
		if (parameters.values().size() > 0){
			throwException(parameters);
		}
		
		validateMail(postulation.getApplicant().getEmail());
		validateConvocatory(postulation.getConvocatory().getId());
		
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

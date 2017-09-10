package com.artsoft.scb.model.bll;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IApplicantService;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.DocumentTypeRepository;
import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.dao.UserTypeRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.DocumentType;
import com.artsoft.scb.model.entity.User;
import com.artsoft.scb.model.entity.UserType;

@Service
public class ApplicantService extends ExceptionService implements IApplicantService  {
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * Repository of document type.
	 */
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private HelperService helperService;
	
	@Value("${Email.SubjectWelcome}")
	private String subjectWelcomeEmail;
	
	@Value("${Email.NameHtmlWelcome}")
	private String pathHtmlWelcomeEmail;
	
	private final String ROLE_APLICANT = "ROLE_APPLICANT";
	/**
	 * Crea el solicitante
	 * @param applicant el solicitante.
	 * @throws Exception : lanza excepcion si alguna propiedad del solicitante no es valida.
	 */
	public boolean createApplicant(Applicant applicant) throws Exception {
		
		validateApplicant(applicant);
		setDocumentType(applicant);
		applicant.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		
		String token = helperService.getGeneratedToken();
		setUser(applicant, token);		
		Applicant applicantSaved = applicantRepository.save(applicant);		
		if (applicantSaved == null) {
			return false;
		}
		
		sendWelcomeEmail(applicant, token);
		return true;
	}
	
	public void approveRegisterApplicant(String token) throws Exception {
		User user = userRepository.findByToken(token);
		if (user == null) {
			throwException("Response", "El usuario no se encontro con ese token.");			
		}
		
		if (user.isEnabled()) {
			throwException("Response", "El usuario ya est� habilitado.");
		}
		
		user.setEnabled(true);
		user.setToken(null);
		
		userRepository.save(user);
	}
	
	private void setUser(Applicant applicant, String token) {
		User user = new User();
		user.setEnabled(false);
		user.setPassword(helperService.encryptPassword(applicant.getPassword()));
		user.setToken(token);
		user.setEmail(applicant.getEmail());
		user = userRepository.save(user);		
			
		UserType userType = new UserType();
		userType.setRol(ROLE_APLICANT);
		userType.setUser(user);
		userType = userTypeRepository.save(userType);
		
		applicant.setUser(user);
	}
	
	private void sendWelcomeEmail(Applicant applicant, String token) throws Exception {
		String link = helperService.getBaseUrl();
		
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = applicant.getFirstName();
		parameters.put("[NAME]", name);
		parameters.put("[URLROOTSYSTEM]", link);
		parameters.put("[TOKEN]", token);
		
		subjectWelcomeEmail = subjectWelcomeEmail.replace("[NAME]", name);
		String bodyEmailToSend = helperService.getEmail(pathHtmlWelcomeEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(applicant.getEmail());		
		
		messageService.sendMessage(bodyEmailToSend, destinies, subjectWelcomeEmail);
	}
	
	
	/**
	 * asocia el tipo de documento al solicitante
	 * @param applicant: el solicitante
	 */
	private void setDocumentType(Applicant applicant) {		
		DocumentType documentType = documentTypeRepository.findOne(applicant.getDocumentType().getId());
		applicant.setDocumentType(documentType);
	}
	
	/**
	 * Valida el solicitante
	 * @param applicant: El solicitante
	 * @throws Exception : lanza excepcion si alguna propiedad del solicitante no es valida.
	 */
	private void validateApplicant(Applicant applicant) throws Exception {
			
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(applicant);
		
		if (constraintViolations.size() > 0) {		    
		    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
		    	parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());		    	
		    }
		}		
		
		if(parameters.values().size()>0){
			throwException(parameters);
		}
		
		ValidatePassword(applicant);
		ValidateEmail(applicant);
		ValidateDocuments(applicant);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateDocuments(Applicant applicant) throws Exception {
		int idDocumentType = applicant.getDocumentType().getId();
		DocumentType documentType = documentTypeRepository.findOne(idDocumentType);
				
		if(documentType == null) {
			throwException("documentType", "No existe ese tipo documento.");
		}
				
		Applicant applicantSearched = applicantRepository.findByDocumentTypeAndDocumentNumber(documentType, applicant.getDocumentNumber());
		if (applicantSearched != null) {
			throwException("documentNumber", "Ya hay un aplicante con ese numero de documento y tipo documento.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El solicitante
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Applicant applicant) throws Exception {
		User userSearched = userRepository.findByEmail(applicant.getEmail());
		if (userSearched != null) {
			throwException("email", "Ya hay existe una cuenta asociada a ese correo");
		}
	}
	
	private void ValidatePassword(Applicant applicant) throws Exception {
		if(applicant.getPassword() == null || applicant.getPassword().isEmpty()) {
			throwException("password", "La clave es requerida.");			
		}
		
		if(applicant.getPassword().length() < 3) {
			throwException("password", "La clave debe ser mayor de 3 caracteres.");
		}
	}
}

	

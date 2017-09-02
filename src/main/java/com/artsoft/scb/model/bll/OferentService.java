package com.artsoft.scb.model.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.bll.interfaces.IOferentService;
import com.artsoft.scb.model.dao.OferentRepository;
import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.dao.UserTypeRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.Oferent;
import com.artsoft.scb.model.entity.User;
import com.artsoft.scb.model.entity.UserType;

@Service
public class OferentService implements IOferentService {
	
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private OferentRepository oferentRepository;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Value("${Email.SubjectDecision}")
	private String subjectDecisionEmail;
	
	@Value("${Email.NameHtmlDecision}")
	private String pathHtmlDecisionEmail;
	
	private final String ROLE_OFERENT = "ROLE_OFERENTE";
	
	/**
	 * Crea el oferente
	 * @param oferent el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	public boolean createOferent(Oferent oferent) throws Exception {
		
		validateOferent(oferent);
		oferent.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		setUser(oferent);
		Oferent oferentSaved = oferentRepository.save(oferent);
		
		if (oferentSaved == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Crea al oferente como un usuario del sistema
	 * @param oferent el oferente
	 */
	private void setUser(Oferent oferent) {

		User user = new User();
		user.setEnabled(false);
		user.setPassword(helperService.encryptPassword(oferent.getPassword()));
		user.setToken("");
		user.setEmail(oferent.getEmail());
		
		user = userRepository.save(user);

		UserType userType = new UserType();
		userType.setRol(ROLE_OFERENT);
		userType.setUser(user);
		userType = userTypeRepository.save(userType);

		oferent.setUser(user);
	}
	
	public void acceptOferent(String nit){
		Oferent oferent = oferentRepository.findByNit(nit);
		oferent.setState("APROBADO");
		oferentRepository.save(oferent);
		
		User user = userRepository.findByEmail(oferent.getEmail());
		user.setEnabled(true);
		user = userRepository.save(user);		
	}
	
	public void rejectOferent(String nit){
		Oferent oferent = oferentRepository.findByNit(nit);
		oferent.setState("RECHAZADO");
		oferentRepository.save(oferent);
	}	
		
	
	private void sendWelcomeEmail(Oferent oferent, String decision) throws IOException {
		String link = helperService.getBaseUrl();
		
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = oferent.getName();
		parameters.put("[NAME]", name);
		parameters.put("[DECISION]", decision);
		String bodyEmailToSend = helperService.getEmail(pathHtmlDecisionEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(oferent.getEmail());		
		

		//sendEmail(bodyEmailToSend, destinies)		
	}
		
	/**
	 * Valida el oferente
	 * @param applicant: El oferente
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	private void validateOferent(Oferent oferent) throws Exception {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Oferent>> constraintViolations = validator.validate(oferent);

		if (constraintViolations.size() > 0) {
		    Set<String> violationMessages = new HashSet<String>();

		    for (ConstraintViolation<Oferent> constraintViolation : constraintViolations) {
		        violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
		    }

		    throw new RuntimeException("Oferent is not valid:\n" + StringUtils.join(violationMessages, "\n"));
		}
		
		ValidatePassword(oferent);
		ValidateEmail(oferent);
		ValidateNit(oferent);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateNit(Oferent oferent) throws Exception {		
		
		Oferent oferentFound = oferentRepository.findByNit(oferent.getNit());		
		if (oferentFound != null) {
			throw new Exception("Ya hay un oferente con ese nit.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El oferente
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Oferent oferent) throws Exception {
		
		Oferent oferentSearched = oferentRepository.findByEmail(oferent.getEmail());
		if (oferentSearched != null) {
			throw new Exception("Ya hay un oferente con ese email.");
		}
	}
	
	private void ValidatePassword(Oferent oferent) throws Exception {
		if(oferent.getPassword() == null || oferent.getPassword().isEmpty()) {
			throw new Exception("La clave es requerida.");
		}
		
		if(oferent.getPassword().length() < 3) {
			throw new Exception("La clave debe ser mayor de 3 caracteres.");
		}
	}
}

	

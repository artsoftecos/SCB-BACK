package com.artsoft.scb.model.bll;

import java.io.IOException;
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

import com.artsoft.scb.model.bll.interfaces.IOffererService;
import com.artsoft.scb.model.dao.OffererRepository;
import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.dao.UserTypeRepository;
import com.artsoft.scb.model.entity.Offerer;
import com.artsoft.scb.model.entity.User;
import com.artsoft.scb.model.entity.UserType;

@Service
public class OffererService extends ExceptionService implements IOffererService {
	
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private OffererRepository oferentRepository;
	
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
	 * @param offerer el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	public boolean createOferent(Offerer offerer) throws Exception {
		validateOferent(offerer);
		offerer.setState("PENDIENTE");
		offerer.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		setUser(offerer);
		Offerer oferentSaved = oferentRepository.save(offerer);
		if (oferentSaved == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Crea al oferente como un usuario del sistema
	 * @param offerer el oferente
	 */
	private void setUser(Offerer offerer) {

		User user = new User();
		user.setEnabled(false);
		user.setPassword(helperService.encryptPassword(offerer.getPassword()));
		user.setToken("");
		user.setEmail(offerer.getEmail());
		
		user = userRepository.save(user);

		UserType userType = new UserType();
		userType.setRol(ROLE_OFERENT);
		userType.setUser(user);
		userType = userTypeRepository.save(userType);

		offerer.setUser(user);
	}
	
	public void acceptOferent(String nit){
		Offerer offerer = oferentRepository.findByNit(nit);
		offerer.setState("APROBADO");
		oferentRepository.save(offerer);
		
		User user = userRepository.findByEmail(offerer.getEmail());
		user.setEnabled(true);
		user = userRepository.save(user);		
	}
	
	public void rejectOferent(String nit){
		Offerer offerer = oferentRepository.findByNit(nit);
		offerer.setState("RECHAZADO");
		oferentRepository.save(offerer);
	}	
		
	
	private void sendWelcomeEmail(Offerer offerer, String decision) throws IOException {
		String link = helperService.getBaseUrl();
		
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = offerer.getName();
		parameters.put("[NAME]", name);
		parameters.put("[DECISION]", decision);
		String bodyEmailToSend = helperService.getEmail(pathHtmlDecisionEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(offerer.getEmail());		
		

		//sendEmail(bodyEmailToSend, destinies)		
	}
		
	/**
	 * obtiene todos los oferentes
	 * @return los oferentes.
	 */
	public List<Offerer> getAllOferents() {
		return (ArrayList<Offerer>)oferentRepository.findAll();
	}
	
	/**
	 * Valida el oferente
	 * @param applicant: El oferente
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	private void validateOferent(Offerer offerer) throws Exception {
		
		Hashtable<String, String> parameters = new Hashtable<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(offerer);
		
		if (constraintViolations.size() > 0) {		    
		    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
		    	parameters.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());		    	
		    }
		}		
		
		if(parameters.values().size()>0){
			throwException(parameters);
		}
		
		ValidatePassword(offerer);
		ValidateEmail(offerer);
		ValidateNit(offerer);
	}
	
	/***
	 * Valida el tipo documento
	 * @param applicant: el solicitante
	 * @throws Exception: lanza excepcion si no es valido el tipo documento.
	 */
	private void ValidateNit(Offerer offerer) throws Exception {		
		
		Offerer oferentFound = oferentRepository.findByNit(offerer.getNit());		
		if (oferentFound != null) {
			throwException("documentNumber", "Ya hay un oferente con ese nit.");
		}
	}
	
	/**
	 * Valida el correo
	 * @param applicant : El oferente
	 * @throws Exception : Lanza excepcion si el email no es valido
	 */
	private void ValidateEmail(Offerer offerer) throws Exception {
		
		Offerer oferentSearched = oferentRepository.findByEmail(offerer.getEmail());
		if (oferentSearched != null) {
			throwException("email", "Ya hay un oferente con ese email.");
		}
	}
	
	private void ValidatePassword(Offerer offerer) throws Exception {
		if(offerer.getPassword() == null || offerer.getPassword().isEmpty()) {
			throwException("password", "La clave es requerida.");
		}
		
		if(offerer.getPassword().length() < 3) {
			throwException("password", "La clave debe ser mayor de 3 caracteres.");			
		}
	}	
}

	

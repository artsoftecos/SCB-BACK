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
import com.artsoft.scb.model.dao.OffererStateRepository;
import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.dao.UserTypeRepository;
import com.artsoft.scb.model.entity.Offerer;
import com.artsoft.scb.model.entity.OffererState;
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
	
	@Autowired
	private OffererStateRepository offererStateRepository;
	
	@Autowired
	private MessageService messageService;
	
	@Value("${Email.SubjectDecision}")
	private String subjectDecisionEmail;
	
	@Value("${Email.NameHtmlApproved}")
	private String pathHtmlApprovedEmail;
	
	@Value("${Email.NameHtmlRejected}")
	private String pathHtmlRejectedEmail;
	
	@Value("${Email.NameHtmlOffererRegistration}")
	private String pathHtmlOffererRegistration;
	
	@Value("${Email.SubjectWelcome}")
	private String subjectWelcomeEmail;
	
		
	private final String ROLE_OFFERER = "ROLE_OFFERER";
	
	private final int ID_PENDING = 1;
	
	private final int ID_APPROVED = 2;
	
	private final int ID_REJECTED = 3;
	
	
	/**
	 * Crea el oferente
	 * @param offerer el oferente.
	 * @throws Exception : lanza excepcion si alguna propiedad del oferente no es valida.
	 */
	public boolean createOferent(Offerer offerer) throws Exception {
		validateOferent(offerer);
		setOffererState(offerer, ID_PENDING);
		offerer.setDateRegister(new java.sql.Timestamp(System.currentTimeMillis()));
		setUser(offerer);
		Offerer oferentSaved = oferentRepository.save(offerer);
		if (oferentSaved == null) {
			return false;
		}
		sendRegistrationEmail(offerer);
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
		userType.setRol(ROLE_OFFERER);
		userType.setUser(user);
		userType = userTypeRepository.save(userType);

		offerer.setUser(user);
	}
	
	public void acceptOferent(String nit) throws Exception{
		Offerer offerer = oferentRepository.findByNit(nit);
		setOffererState(offerer, ID_APPROVED);
		oferentRepository.save(offerer);
		
		User user = userRepository.findByEmail(offerer.getEmail());
		user.setEnabled(true);
		user = userRepository.save(user);
		
		sendApprovedMessage(offerer);
	}
	
	public void rejectOferent(String nit) throws Exception{
		Offerer offerer = oferentRepository.findByNit(nit);
		setOffererState(offerer, ID_REJECTED);
		oferentRepository.save(offerer);
		
		User user = userRepository.findByEmail(offerer.getEmail());
		user.setEnabled(false);
		user = userRepository.save(user);
		
		sendRejectedMessage(offerer);
	}	
		
	private void setOffererState(Offerer offerer, int id) {	
		OffererState offererState = offererStateRepository.findOne(id);
		offerer.setOffererState(offererState);
		
	}
	
	private void sendApprovedMessage(Offerer offerer) throws Exception {
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = offerer.getName();
		parameters.put("[NAME]", name);
		String bodyEmailToSend = helperService.getEmail(pathHtmlApprovedEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(offerer.getEmail());		
		messageService.sendMessage(bodyEmailToSend, destinies, subjectDecisionEmail);
			
	}
	
	private void sendRejectedMessage(Offerer offerer) throws Exception {
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = offerer.getName();
		parameters.put("[NAME]", name);
		String bodyEmailToSend = helperService.getEmail(pathHtmlRejectedEmail, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(offerer.getEmail());
		messageService.sendMessage(bodyEmailToSend, destinies, subjectDecisionEmail);
			
	}
	
	private void sendRegistrationEmail(Offerer offerer) throws Exception{
		Hashtable<String, String>  parameters = new Hashtable<String, String>();
		String name = offerer.getName();
		parameters.put("[NAME]", name);
		String bodyEmailToSend = helperService.getEmail(pathHtmlOffererRegistration, parameters);
		List<String> destinies = new ArrayList<String>();
		destinies.add(offerer.getEmail());	
		subjectWelcomeEmail = subjectWelcomeEmail.replace("[NAME]", name);
		messageService.sendMessage(bodyEmailToSend, destinies, subjectWelcomeEmail);
	}
		
	/**
	 * obtiene todos los oferentes
	 * @return los oferentes.
	 */
	public List<Offerer> getAllOferents() {
		return (ArrayList<Offerer>)oferentRepository.findAll();
	}
	
	/**
	 * obtiene todos los oferentes pendientes
	 * @return los oferentes.
	 */
	public List<Offerer> getPendingOferents() {
		OffererState offererState = offererStateRepository.findOne(ID_PENDING);
		return (ArrayList<Offerer>)oferentRepository.findByOffererState(offererState);
	}
	
	/**
	 * obtiene todos los oferentes aprobados
	 * @return los oferentes.
	 */
	public List<Offerer> getApprovedOferents() {
		OffererState offererState = offererStateRepository.findOne(ID_APPROVED);
		return (ArrayList<Offerer>)oferentRepository.findByOffererState(offererState);
	}
	
	/**
	 * obtiene todos los oferentes rechazados
	 * @return los oferentes.
	 */
	public List<Offerer> getRejectedOferents() {
		OffererState offererState = offererStateRepository.findOne(ID_REJECTED);
		return (ArrayList<Offerer>)oferentRepository.findByOffererState(offererState);
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
		
		User userSearched = userRepository.findByEmail(offerer.getEmail());
		if (userSearched != null) {
			throwException("email", "Ya hay existe una cuenta asociada a ese correo");
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

	

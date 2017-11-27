package com.artsoft.scb.model.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artsoft.scb.model.bll.interfaces.IDocumentService;
import com.artsoft.scb.model.dao.ApplicantDocumentRepository;
import com.artsoft.scb.model.dao.ApplicantRepository;
import com.artsoft.scb.model.dao.ConvocatoryRepository;
import com.artsoft.scb.model.dao.PhaseRepository;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.ApplicantDocument;
import com.artsoft.scb.model.entity.Phase;

@Service
public class ConvocatoryDocumentService extends ExceptionService {
	@Autowired
	private IDocumentService documentService; 
		
	/*@Value("${ApplicantDocument.allowedFileFormats}")
	private String[] allowedFormats;*/
	
	/*@Value("${ApplicantDocument.amountAllowedFiles}")
	private int amountAllowedFiles; */
	
	@Value("${ConvocatoryDocument.allowedFileSize}")
	private int allowedSizeDocument;
			
	/**
	 * Repository of applicant.
	 */
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private PhaseRepository phaseRepository;
	
	public ConvocatoryDocumentService(@Value("${ConvocatoryDocument.FolderName}") String folderName, IDocumentService documentService) {
		this.documentService = documentService;
		this.documentService.setDomainName(folderName);
	}
	
	public void uploadDocument(MultipartFile file, String name, String email, String idPhase, HttpServletRequest request) 
			throws Exception {		
		validateDocument(file, email, name, idPhase);
		Phase phase = phaseRepository.findById(Integer.parseInt(idPhase));
		String idConvocatory = String.valueOf(phase.getConvocatory().getId());
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = name;		
		
		File temp = getConvertedFile(file);
		email = idConvocatory+"/"+idPhase+"/" + email;
		documentService.upload(temp, fileName, email, request);		
		//saveDocument(fileName, name, email, fileName);
	}
	
	/*public void deleteDocument(Long id, HttpServletRequest request ) throws Exception {
		ApplicantDocument document = getApplicantDocument(id);
		if (document == null) {
			throwException("Response", "No se encontro el archivo a eliminar.");			
		}
		
		documentService.delete(document.getApplicant().getEmail(), document.getFileName(), request);	
		deleteDocument(document);
	}*/
	
	/*public List<ApplicantDocument> getDocuments(String email) throws Exception {
		validateEmail(email);
		Applicant applicant = applicantRepository.findByEmail(email);
		List<ApplicantDocument> documents = getDocuments(applicant);
		return documents;
	}*/
	
	public File getDocument(String documentName, String email, String idPhase, HttpServletRequest request) throws Exception {
		Phase phase = phaseRepository.findById(Integer.parseInt(idPhase));
		String idConvocatory = String.valueOf(phase.getConvocatory().getId());
		email = idConvocatory+"/"+idPhase+"/" + email;		
		File file = documentService.get(documentName, email, request);
		return file;
	}

	/*private void saveDocument(String fileName, String name, String email, String path) {		
		//TODO: aca q la fase_soliciatnte.. update? no nada devuelva el valor
		ApplicantDocument applicantDocument = new ApplicantDocument();
		applicantDocument.setFileName(fileName);
		applicantDocument.setName(name);
		applicantDocument.setFileName(path);		
		applicantDocument.setApplicant(setApplicant(email));
		
		applicantDocumentRepository.save(applicantDocument);
	}*/
	
	
	private File getConvertedFile(MultipartFile file) throws IOException {
		
		String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		byte[] bytes = file.getBytes();
	    File temp = File.createTempFile(fileName, "."+extension);
	    FileOutputStream fos = new FileOutputStream(temp);
	    fos.write(bytes);
	    fos.close();		
	    return temp;
	}
	
	private void validateDocument(MultipartFile file, String email, String fileName, String idPhase) throws Exception {
		if (file == null) {
			throwException("Response", "El archivo es requerido.");
		}
		
		if (fileName == null || fileName.isEmpty()) {
			throwException("Response", "El nombre es requerido.");
		}
		
		if (idPhase == null || idPhase.isEmpty()) {
			throwException("Response", "El idPhase es requerido.");
		}
		
		//String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		//String completefileName = fileName + "."+ extension;
		
		validateEmail(email);
		//validateAmountFiles(email, fileName, completefileName);
		validateSizeDocument(file);		
		//validateFormatFiles(file);
	}
	
	private void validateEmail(String email) throws Exception {
		if (email == null || email.isEmpty()) {
			throwException("Response", "El email es requerido.");
		}
		
		Applicant applicant = applicantRepository.findByEmail(email);
		if (applicant == null) {
			throwException("Response", "No se encontro al aplicante con email: "+ email);
		}
	}
	
	private void validateSizeDocument(MultipartFile file) throws Exception {
		long sizeBytes = file.getSize();		
		long sizeInMb = sizeBytes / (1024 * 1024);
		
		if (sizeInMb > allowedSizeDocument) {
			throwException("Response", "El tamaño del archivo no debe ser mayor a " + allowedSizeDocument + " MB.");
		}
	}
	
	/*private void validateAmountFiles(String email, String fileName, String completeFileName) throws Exception {
		Applicant applicant = applicantRepository.findByEmail(email);
		List<ApplicantDocument> documents = getDocuments(applicant);
		
		if (documents.size() >= amountAllowedFiles) {
			throwException("Response", "Solo se permiten "+ amountAllowedFiles+" por persona.");
		}
		
		boolean existDocument = documents.stream().anyMatch(document -> document.getFileName().toLowerCase().equals(completeFileName.toLowerCase()));
		if (existDocument) {
			throwException("Response", "Ese documento ya existe.");
		}
		
		boolean existName = documents.stream().anyMatch(document -> document.getName().toLowerCase().equals(fileName.toLowerCase()));
		if (existName) {
			throwException("Response", "Ese nombre de documento ya existe.");
		}
	}*/
	
	/*private void validateFormatFiles(MultipartFile file) throws Exception{
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		boolean existExtension = Arrays.asList(allowedFormats).contains(extension);
		if (!existExtension) {
			throwException("Response", "Solo son permitidos estos formatos: " + Arrays.toString(allowedFormats) + ".");
		}
	}*/
}

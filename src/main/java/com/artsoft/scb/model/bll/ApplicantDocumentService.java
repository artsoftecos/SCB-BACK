package com.artsoft.scb.model.bll;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApplicantDocumentService {
	
	private IDocumentService documentService; 
		
	@Value("${ApplicantDocument.allowedFileFormats}")
	private String[] allowedFormats;
	
	@Value("${ApplicantDocument.amountAllowedFiles}")
	private int amountAllowedFiles; 
	
	@Value("${ApplicantDocument.allowedFileSize}")
	private int allowedSizeDocument;
		
	public ApplicantDocumentService(@Value("${ApplicantDocument.FolderName}") String folderName, IDocumentService documentService) {
		this.documentService = documentService;
		this.documentService.setDomainName(folderName);
	}
	
	public void uploadDocument(MultipartFile file, String path, String email) throws Exception {
		
		validateDocument(file, email);
		
		String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		byte[] bytes = file.getBytes();
	    File temp = File.createTempFile(fileName, "."+extension);
	    FileOutputStream fos = new FileOutputStream(temp);
	    fos.write(bytes);
	    fos.close();		
		documentService.upload(temp, fileName, email);		
	}
	
	private void validateDocument(MultipartFile file, String email) throws Exception {
		if (file == null) {
			throw new Exception("El archivo es requerido."); 
		}
		
		if (email == null || email.isEmpty()) {
			throw new Exception("El email es requerido.");
		}
		
		//TODO: Validar formato email
		
		validateSizeDocument(file);
		validateAmountFiles(email);
		validateFormatFiles(file);
	}
	
	private void validateSizeDocument(MultipartFile file) throws Exception {
		long sizeBytes = file.getSize();		
		long sizeInMb = sizeBytes / (1024 * 1024);
		
		if (sizeInMb > allowedSizeDocument) {
			throw new Exception("El tamaño del archivo no debe ser mayor a " + allowedSizeDocument + " MB."); 
		}
	}
	
	private void validateAmountFiles(String email) {
		
	}
	
	private void validateFormatFiles(MultipartFile file) throws Exception{
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		boolean existExtension = Arrays.asList(allowedFormats).contains(extension);
		if (!existExtension) {
			throw new Exception("Solo son permitidos estos formatos: " + Arrays.toString(allowedFormats) + ".");
		}
	}
	
	//private void uploadDocument
	
	
	/*
	
	
	
	+ 
	+ GetDocuments(String email) (los que estan en bd)
	+ DeleteDocument(String email, String fileName); (de la bd y del s3)
	- validateDocument(MultipartFile file, string path, String email)
	- saveDocument(String fileName, String path);
	- validateSizeDocument(MultipartFile file)
	- validateAmountFiles(String email)
	- validateFormatFiles(MultipartFile file)
*/
}

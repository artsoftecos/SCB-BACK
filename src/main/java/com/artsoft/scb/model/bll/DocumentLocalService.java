package com.artsoft.scb.model.bll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.artsoft.scb.model.bll.interfaces.IDocumentService;

public class DocumentLocalService extends ExceptionService  implements IDocumentService {
	
	private String domainName;
	private boolean isFileStorageAsync;	
	
	public DocumentLocalService(@Value("#{ @environment['Document.AsyncUpload'] ?: 0 }")boolean isAsync) {
		this.isFileStorageAsync = isAsync;
	}
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public void upload(File file, String fileName, String folderName, HttpServletRequest request) 
			throws AmazonServiceException, AmazonClientException, InterruptedException, IOException, JSONException {	
		try {			
			String path = request.getSession().getServletContext().getRealPath("/")+this.domainName;		
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdir(); 
			}
			path = path +"/"+ folderName;
			folder = new File(path);
			if (!folder.exists()) {
				folder.mkdir(); 
			}
			
			String filePath = path + "/" + fileName;
			byte[] bytes = Files.readAllBytes(file.toPath());
			Path finalPath = Paths.get(filePath);
			Files.write(finalPath, bytes);
		}
		catch(Exception e) {
			throwException("Response", e.getMessage() + e.toString());
		}
	}
	
	public File get(String fileName, String folderName, HttpServletRequest request) 
			throws Exception {
		File file = null;
		try {			
			String path = request.getSession().getServletContext().getRealPath("/")+this.domainName+"/"+folderName;		
			file = new File(path + "/" +fileName);			
		} catch (Exception e) {	
			throwException("Response", e.getMessage() + e.toString());
		}
		return file;
	}
	
	public ArrayList<String> getFiles(String folderName, HttpServletRequest request) throws Exception {
		ArrayList<String> Files = new ArrayList<String>();
		try {			
			String path = request.getSession().getServletContext().getRealPath("/")+this.domainName+"/"+folderName;
			File folder = new File(path);
			if (!folder.exists()) {
				return Files;
			}
			
			File[] files = folder.listFiles();
			for(File file : files) {
				Files.add(file.getName());
			}			
		}
		catch (Exception e) {
			throwException("Response", e.getMessage() + e);
		}
		return Files;
	}

	public void delete(String folderName, String fileName, HttpServletRequest request) throws Exception {		
		try {
			String file = folderName + "/" + fileName;
			String path = request.getSession().getServletContext().getRealPath("/")+this.domainName+"/"+file;
			File currentfile = new File(path);
			if (!currentfile.exists()) {
				throw new Exception("Archivo " + file + "no existe.");
			}
			currentfile.delete();			
		}
		catch (Exception e) {
			throwException("Response", e.getMessage() + e);
		}
	}
}

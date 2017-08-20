package com.artsoft.scb.model.bll;

import java.io.File;
import java.util.ArrayList;

public interface IDocumentService {
	
	void setDomainName(String domainName);
	
	void upload(File file, String fileName, String folderName) throws Exception;
	
	File get(String fileName, String folderName, String extension) throws Exception;
	
	ArrayList<String> getFiles(String folderName) throws Exception;
	
	void delete(String key) throws Exception;
}

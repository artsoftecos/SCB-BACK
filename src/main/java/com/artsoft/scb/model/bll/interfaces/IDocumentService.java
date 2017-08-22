package com.artsoft.scb.model.bll.interfaces;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public interface IDocumentService {
	
	void setDomainName(String domainName);
	
	void upload(File file, String fileName, String folderName, HttpServletRequest request) throws Exception;
	
	File get(String fileName, String folderName, HttpServletRequest request) throws Exception;
	
	ArrayList<String> getFiles(String folderName, HttpServletRequest request) throws Exception;
	
	void delete(String folderName, String fileName, HttpServletRequest request) throws Exception;
}

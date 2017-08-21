package com.artsoft.scb.model.bll;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HelperService {
	
	@Value("${Email.Folder}")
	private String folderEmails;
	
	@Value("${UrlRootSystem}")
	private String rootSystemUrl;
	
	public String getGeneratedToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}
	
	public String getEmail(String nameEmail, Hashtable<String, String> parameters) throws IOException {
		String path = folderEmails + "/" + nameEmail;
		ClassPathResource resource = new ClassPathResource(path);
		InputStream inputStream = resource.getInputStream();
		String file = "";
		
		while( inputStream.available() > 0 ){             	
            file += (char)inputStream.read();
        }
		
		if (parameters == null || parameters.isEmpty()) {
			return file;
		}
		
		Enumeration<String> keys = parameters.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            file = file.replace(key, parameters.get(key).toString());            
        }
		return file;	
	}
	
	public String getBaseUrl() {
		return rootSystemUrl;
	}
	
}

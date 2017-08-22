package com.artsoft.scb.model.bll;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	 /// <summary>
    /// Remueve todos los tags del html
    /// </summary>
    /// <param name="htmlText">The html text</param>
    /// <returns>The string without the tags</returns>
    public String RemoveHtmlTags(String htmlText)
    {
        if (htmlText == null || htmlText.isEmpty())
        {
            return htmlText;
        }
        
        Pattern p = Pattern.compile("<(.|\n)*?>",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        return p.matcher(htmlText).replaceAll("");        
    }
	
}

package com.artsoft.scb.model.bll;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	public String encryptPassword(String password){
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		return pe.encode(password);
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

	public String returnEmail(String credentialsEncoded){
		String [] credenciales = decode(credentialsEncoded).split(":");
		return credenciales[0];
	}
	
	/*
	 * Decodifica una cadena en base 64
	 */
	private String decode(String encoded){
		byte[] decodedValue = Base64.getDecoder().decode(encoded);  // Basic Base64 decoding
	    return new String(decodedValue, StandardCharsets.UTF_8);		
	}	
	
	public String GetConvertedJson(Hashtable<String, String> parameters) throws JSONException {		
		
		JSONObject json = new JSONObject();		
		Enumeration<String> keys = parameters.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            json.put(key, parameters.get(key));            
        }
        
		return json.toString();
	}	
}

package com.artsoft.scb.model.bll;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

public class ExceptionService {
	
	public String throwException(String key, String value) throws JSONException {		
		JSONObject error = new JSONObject();
		error.put(key, value);
		throw new RuntimeException(error.toString());
	}
	
	public String throwException(Hashtable<String, String> parameters) throws JSONException {		
		JSONObject ob = new JSONObject(parameters);
		throw new RuntimeException(ob.toString());
	}

}

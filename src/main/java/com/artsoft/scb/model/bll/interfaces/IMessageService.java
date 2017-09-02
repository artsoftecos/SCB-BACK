package com.artsoft.scb.model.bll;

import java.util.List;

public interface IMessageService {
	
	boolean sendMessage(String template, List<String> destinies, String subject) throws Exception;

}

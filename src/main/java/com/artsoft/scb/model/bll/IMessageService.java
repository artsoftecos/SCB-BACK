package com.artsoft.scb.model.bll;

import java.util.List;

public interface IMessageService {
	
	boolean sendMessage(String template, String textBody, List<String> destinies, String subject) throws Exception;

}

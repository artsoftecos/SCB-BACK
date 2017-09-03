package com.artsoft.scb.model.bll.interfaces;

import java.util.List;

public interface IMessageService {
	
	boolean sendMessage(String template, List<String> destinies, String subject) throws Exception;

}

package com.artsoft.scb.model.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.AWScredentialRepository;
import com.artsoft.scb.model.entity.AWScredential;

@Service
public class AWScredentialService {
	
	@Autowired
	AWScredentialRepository  aesCredentialRepository;
	
	public List<AWScredential> getCredentials() {
		return (List<AWScredential>) aesCredentialRepository.findAll();
	}

	
}

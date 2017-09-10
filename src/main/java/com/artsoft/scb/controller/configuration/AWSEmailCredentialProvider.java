package com.artsoft.scb.controller.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.artsoft.scb.model.bll.AWScredentialService;
import com.artsoft.scb.model.entity.AWScredential;

@Service
public class AWSEmailCredentialProvider implements AWSCredentialsProvider {
    
	@Autowired
	private AWScredentialService awsCredentialService;
    
    private String accessKey;
    private String secretKey;

    private void init() {    	
    }

    @Override
    public AWSCredentials getCredentials() {
    	if(accessKey == null || accessKey.isEmpty() || secretKey == null || secretKey.isEmpty()) {
	    	List<AWScredential> credentialList = awsCredentialService.getCredentials();    	
			if (credentialList != null && credentialList.size() > 0) {
				 accessKey = credentialList.get(0).getValue();
				secretKey = credentialList.get(1).getValue();
			}
    	}
      return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Override
    public void refresh() {

    }   
  }
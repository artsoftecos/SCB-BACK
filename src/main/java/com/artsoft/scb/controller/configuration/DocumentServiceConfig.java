package com.artsoft.scb.controller.configuration;

import org.json.JSONException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artsoft.scb.aws.DocumentService;
import com.artsoft.scb.model.bll.DocumentLocalService;
import com.artsoft.scb.model.bll.interfaces.IDocumentService;

@Configuration
public class DocumentServiceConfig {
	
	@Bean
    @ConditionalOnProperty(name = "Document.uploadType", havingValue = "Local", matchIfMissing = true)
    public IDocumentService localDocumentService() {		
        return new DocumentLocalService(true);
    }
 
    @Bean
    @ConditionalOnProperty(name = "Document.uploadType", havingValue = "Amazon")
    public IDocumentService cloudDocumentService() throws JSONException {
        return new DocumentService(true);
    }
   
}

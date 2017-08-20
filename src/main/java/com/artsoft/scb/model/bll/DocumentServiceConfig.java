package com.artsoft.scb.model.bll;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.artsoft.scb.aws.DocumentService;

@Configuration
public class DocumentServiceConfig {
	
	//@Value("${Document.AsyncUpload}")
	//private boolean asyncUpload;
	
	@Bean
    @ConditionalOnProperty(name = "Document.uploadType", havingValue = "Local", matchIfMissing = true)
    public IDocumentService localDocumentService() {		
        return new DocumentLocalService(true);
    }
 
    @Bean
    @ConditionalOnProperty(name = "Document.uploadType", havingValue = "Amazon")
    public IDocumentService cloudDocumentService() {
        return new DocumentService(true);
    }
   
}

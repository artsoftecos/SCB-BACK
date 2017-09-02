package com.artsoft.scb.controller;



import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.artsoft.scb.controller.configuration.CorssConfiguration;
import com.artsoft.scb.controller.configuration.SecurityConfiguration;
import com.artsoft.scb.model.bll.ApplicantService;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.DocumentType;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
@RunWith(SpringRunner.class)
@WebMvcTest(ApplicantController.class)
@ContextConfiguration(classes={SecurityConfiguration.class, CorssConfiguration.class})
public class ApplicantControllerTest {

	@TestConfiguration
    static class ApplicantControllerTestContextConfiguration {
  
        @Bean
        public UserDetailsService userService() throws Exception {
            return new UserDetailsService() {
				
				@Override
				public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
					// TODO Auto-generated method stub
					return null;
				}
			};
        }
    }
	
	@Autowired
    private MockMvc mvc;
 
	@MockBean
	private UserDetailsService userService;
	    
    @MockBean
    private ApplicantService applicantService;
 
    @Autowired 
    private ObjectMapper mapper;
    
    @Autowired
	private WebApplicationContext context;
    
    @Before
	public void setup() {
    	mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
    	}
    
    @Test
    public void CreateApplicant_whenCreateApplicant_thenReturnOk()
      throws Exception {
         
    	DocumentType documentType = new DocumentType();
        documentType.setId(1);
        
    	Applicant alex = new Applicant();
        alex.setEmail("alex@hotmail.com");
        alex.setDocumentType(documentType);
        
        Mockito.when(
        		applicantService.createApplicant(alex)).thenReturn(true);
        String json = mapper.writeValueAsString(alex);
        mvc.perform(post("/applicant")
        		.accept(MediaType.APPLICATION_JSON)
        		.content(json)
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
    @Test
    public void CreateApplicant_whenCreateApplicant_thenoReturnOk() throws Exception
    	    {
    	        mvc.perform(get("/applicant"))
    	        .andExpect(status().isOk());
    	    }
    
    @Test
    public void ApproveApplicant_whenApproveApplicant_thenReturnOk()
      throws Exception {
         
    	Mockito.doNothing().when(applicantService).approveRegisterApplicant("12");        
        mvc.perform(get("/applicant/welcome/12")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}

*/
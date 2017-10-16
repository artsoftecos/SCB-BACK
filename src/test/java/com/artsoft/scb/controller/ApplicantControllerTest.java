package com.artsoft.scb.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.artsoft.scb.model.bll.ApplicantService;
import com.artsoft.scb.model.entity.Applicant;
import com.artsoft.scb.model.entity.DocumentType;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
//@WebAppConfiguration (no va)
//@Import(SecurityConfiguration.class)
@WebMvcTest(controllers = ApplicantController.class, secure = true)
public class ApplicantControllerTest{
//extends BaseControllerTest {

//	@TestConfiguration
//    static class ApplicantControllerTestContextConfiguration {
//  
//        @Bean("userService")
//        public UserDetailsService userService() throws Exception {
//            return new UserDetailsService() {
//				
//				@Override
//				public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			};
//        }
//    }
	
	@Autowired
    private MockMvc mvc;
 
	//@MockBean
	//private UserDetailsService userService;
	    
    @MockBean
    private ApplicantService applicantService;
 
    @Autowired 
    private ObjectMapper mapper;
        
//    @Before
//	public void setup() {
//    	mvc = MockMvcBuilders//.standaloneSetup(new ApplicantController())
//				.webAppContextSetup(context)
//				.apply(springSecurity())
//				.build();
//    	}
    
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
    public void ApproveApplicant_whenApproveApplicant_thenReturnOk()
      throws Exception {
         
    	Mockito.doNothing().when(applicantService).approveRegisterApplicant("12");        
        mvc.perform(get("/applicant/welcome/12")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
	@Test
	public void RootIsAccesible() throws Exception {
		mvc.perform(get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}


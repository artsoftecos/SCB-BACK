package com.artsoft.scb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.artsoft.scb.model.bll.OferentService;
import com.artsoft.scb.model.entity.Oferent;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
//@WebMvcTest(OferentController.class)
//public class OferentControllerTest {
//
//	@Autowired
//    private MockMvc mvc;
// 
//    @MockBean
//    private OferentService oferentService;
// 
//    @Autowired 
//    private ObjectMapper mapper;
//    
//    @Test
//    public void CreateOferent_whenCreateOferent_thenReturnOk()
//      throws Exception {
//         
//    	Oferent alex = new Oferent();
//    	alex.setEmail("alex@hotmail.com");    
//    	alex.setNit("1014207");
//    	alex.setName("Icetex");	    	
//        alex.setPassword("Hola");
//    
//        Mockito.when(
//        		oferentService.createOferent(alex)).thenReturn(true);
//        String json = mapper.writeValueAsString(alex);
//        mvc.perform(post("/oferent")
//        		.accept(MediaType.APPLICATION_JSON)
//        		.content(json)
//        		.contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
//    }
//    
//}
//

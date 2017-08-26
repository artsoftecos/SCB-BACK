package com.artsoft.scb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

@RunWith(SpringRunner.class)
@WebMvcTest(OferentController.class)
public class OferentControllerTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private OferentService oferentService;
 
    @Autowired 
    private ObjectMapper mapper;
    
    @Test
    public void CreateOferent_whenCreateOferent_thenReturnOk()
      throws Exception {
         
    	Oferent alex = new Oferent();
    	alex.setEmail("alex@hotmail.com");    
    	alex.setNit("1014207");
    	alex.setName("Icetex");	    	
        alex.setPassword("Hola");
    
        Mockito.when(
        		oferentService.createOferent(alex)).thenReturn(true);
        String json = mapper.writeValueAsString(alex);
        mvc.perform(post("/oferent")
        		.accept(MediaType.APPLICATION_JSON)
        		.content(json)
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
    @Test
    public void GetAllOferents_whenGetAllOferents_thenReturnOk()
      throws Exception {
         
    	List<Oferent> oferents = new ArrayList<Oferent>();
    	Oferent oferentOne = new Oferent();
		oferentOne.setName("Oferent one");
		oferentOne.setNit("123456");
		oferents.add(oferentOne);
		
		Mockito.when(oferentService.getAllOferents()).thenReturn(oferents);
		
        mvc.perform(get("/oferent")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}


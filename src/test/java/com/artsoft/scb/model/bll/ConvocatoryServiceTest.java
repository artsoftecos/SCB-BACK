package com.artsoft.scb.model.bll;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.artsoft.scb.model.dao.ConvocatoryStateRepository;


@RunWith(SpringRunner.class)
public class ConvocatoryServiceTest {
	
	
	@TestConfiguration
	static class ConvocatoryServiceContextConfiguration {

		@Bean
		public ConvocatoryService convocatoryService() {
			return new ConvocatoryService();
		}
	}
	
	
	@Autowired
	private ConvocatoryService convocatoryService;


	@Test
	public void getByOffererState() {

		try {
			convocatoryService.getByOffererState("offererRejected@artsoft.com", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

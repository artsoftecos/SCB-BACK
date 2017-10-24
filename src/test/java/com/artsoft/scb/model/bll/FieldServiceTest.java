package com.artsoft.scb.model.bll;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.artsoft.scb.model.entity.Field;

@RunWith(SpringRunner.class)
public class FieldServiceTest {
	
	@TestConfiguration
	static class FieldServiceTestContextConfiguration {
		@Bean
		public FieldService fieldService() {
           return new FieldService();
		}
	}
	
	@Autowired
	private FieldService fieldService;

	@Test
	public void list() {
		List<Field> fields;
		try {
			fields = fieldService.findByPhase(1);
			for (Field field : fields) {
				System.out.println(field.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

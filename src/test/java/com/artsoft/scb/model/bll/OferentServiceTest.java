package com.artsoft.scb.model.bll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.artsoft.scb.model.dao.OffererRepository;

//
////http://www.baeldung.com/spring-boot-testing
//@RunWith(SpringRunner.class)
//public class OferentServiceTest {
//
//	@TestConfiguration
//	static class OferentServiceTestContextConfiguration {
//
//		@Bean
//		public OferentService oferentService() {
//			return new OferentService();
//		}
//	}
//
//	@Autowired
//	private OferentService oferentService;
//
//	@MockBean
//	private OferentRepository oferentRepository;
//
//	@Before
//	public void setUp() {
//
//	}
//
//	@Test
//	public void whenNoExistsNit_thenException() {
//
//		boolean isCreated = false;
//		Oferent alex = new Oferent();
//		alex.setEmail("alex@hotmail.com");
//		// alex.setNit("1014207");
//		alex.setName("Icetex");
//		alex.setPassword("hola");
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//			fail();
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
//		assertFalse(isCreated);
//	}
//
//	@Test
//	public void whenNoEmail_thenException() {
//
//		boolean isCreated = false;
//		Oferent alex = new Oferent();
//		// alex.setEmail("alex@hotmail.com");
//		alex.setNit("1014207");
//		alex.setName("Icetex");
//		alex.setPassword("hola");
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//			fail();
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
//		assertFalse(isCreated);
//	}
//
//	@Test
//	public void whenWrongEmail_thenException() {
//
//		boolean isCreated = false;
//		Oferent alex = new Oferent();
//		alex.setEmail("alexhotmail.com");
//		alex.setNit("1014207");
//		alex.setName("Icetex");
//		alex.setPassword("hola");
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//			fail();
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
//		assertFalse(isCreated);
//	}
//
//	@Test
//	public void whenNoPassword_thenException() {
//
//		boolean isCreated = false;
//		Oferent alex = new Oferent();
//		alex.setEmail("alex@hotmail.com");
//		alex.setNit("1014207");
//		alex.setName("Icetex");
//		// alex.setPassword("hola");
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//			fail();
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
//		assertFalse(isCreated);
//	}
//
//	@Test
//	public void whenEmptyPassword_thenException() {
//
//		boolean isCreated = false;
//		Oferent alex = new Oferent();
//		alex.setEmail("alex@hotmail.com");
//		alex.setNit("1014207");
//		alex.setName("Icetex");
//		alex.setPassword("");
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//			fail();
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
//		assertFalse(isCreated);
//	}
//
//	@Test
//	public void whenValidOferent_thenOferentIsSaved() {
//
//		boolean isCreated = false;
//
//		Oferent alex = new Oferent();
//		alex.setEmail("alex@hotmail.com");
//		alex.setNit("1014207");
//		alex.setName("Icetex");
//		alex.setPassword("Hola");
//
//		Mockito.when(oferentRepository.save(alex)).thenReturn(alex);
//
//		try {
//			isCreated = oferentService.createOferent(alex);
//		} catch (Exception e) {
//			fail();
//		}
//		assertTrue(isCreated);
//	}
//	
//	@Test
//	public void getAllOferents_WhenRequestAllOferents_thenReturnOferentsInDb() {
//		
//		List<Oferent> oferents = new ArrayList<Oferent>();
//		Oferent oferentOne = new Oferent();
//		oferentOne.setName("Oferent one");
//		oferentOne.setNit("123456");
//		oferents.add(oferentOne);
//		
//		Mockito.when(oferentRepository.findAll()).thenReturn(oferents);
//		
//		List<Oferent> listOferents = new ArrayList<Oferent>();
//		
//		try {
//			listOferents = oferentService.getAllOferents();			
//		} catch(Exception ex) {
//			fail();
//		}
//		assertNotNull(listOferents);
//		assertEquals(listOferents.size(), 1);
//		assertEquals(listOferents.get(0).getName(), oferentOne.getName());
//	}
//
//}

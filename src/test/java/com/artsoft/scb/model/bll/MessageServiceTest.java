package com.artsoft.scb.model.bll;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
public class MessageServiceTest {
	
//	@TestConfiguration
//    static class MessageServiceImplTestContextConfiguration {
//  
//        @Bean
//        public MessageService messageService() {
//            return new MessageService();
//        }
//    }
//	
//	@Autowired
//	private MessageService ms; 
//	
//	@Before
//    public void setUp() {
//        
//    }
//	
////	@Test
//	public void sentMessageTest() throws Exception {
//		
//		String msgHtml = "<h1>Test Mails</h1>"
//				+ "<p>La peor forma de extrañar a alguien es estar sentado a su lado y saber que nunca lo podrás tener."
//				+ "La memoria del corazón elimina los malos recuerdos y magnifica los buenos, y gracias a ese artificio, logramos sobrellevar el pasado.";
//		
//		String msg = "This email was sent with AWS";
//		String subject = "unitTest";
//		
//		List<String> listMails = new ArrayList<String>();
//		
//		listMails.add("dknieto.06@gmail.com");
//		listMails.add("darthian007@gmail.com");
//		listMails.add("iansm@hotmail.com");
//		
//		Boolean flag = ms.sendMessage(msgHtml, msg, listMails, subject);
//		assertTrue(flag);
//	} 

}
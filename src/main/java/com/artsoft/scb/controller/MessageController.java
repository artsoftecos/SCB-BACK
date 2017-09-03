package com.artsoft.scb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.MessageService;
import com.artsoft.scb.model.entity.Message;

@RestController
@RequestMapping(path = "/message")
public class MessageController {
	
	@Autowired
	MessageService ms;
	
	@PostMapping(path = "/sent")
	public ResponseEntity<?> createApplicant(@RequestBody Message message) {
		
		try {
			ms.sendMessage(message.getHtmlBody(), message.getDestinies(), message.getSubject());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Message sent!");
	}

}

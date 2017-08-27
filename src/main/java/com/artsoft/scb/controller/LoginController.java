package com.artsoft.scb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.scb.model.bll.HelperService;
import com.artsoft.scb.model.bll.UserService;
import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.entity.User;
import com.artsoft.scb.model.entity.UserLoginDetails;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HelperService helperService;
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestHeader("Authorization") String credentials){
		String email = helperService.returnEmail(credentials.split(" ")[1]);
		UserLoginDetails userLoginDetails = userService.getUser(email);
		return ResponseEntity.status(HttpStatus.OK).body(userLoginDetails);
	}
	
	
}

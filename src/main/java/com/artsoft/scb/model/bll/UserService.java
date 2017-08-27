package com.artsoft.scb.model.bll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artsoft.scb.model.dao.UserRepository;
import com.artsoft.scb.model.entity.UserLoginDetails;
import com.artsoft.scb.model.entity.UserType;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.artsoft.scb.model.entity.User user = userRepository.findByEmail(email);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserType());
		return buildUser(user, authorities);
	}
	
	public UserLoginDetails getUser(String email){
		UserLoginDetails userLoginDetails = new UserLoginDetails();
		com.artsoft.scb.model.entity.User user = userRepository.findByEmail(email);
		
		userLoginDetails.setEnabled(user.isEnabled());
		if(user.getApplicant() != null){
			userLoginDetails.setFullNameAplicant(user.getApplicant().getFirstName(), user.getApplicant().getSecondName(), user.getApplicant().getFirstLastName(), user.getApplicant().getSecondLastName());
		}else if(user.getOfferent()!= null){
			userLoginDetails.setNameOferent(user.getOfferent().getName());
		}
		
		userLoginDetails.setRole(user.getUserType().getRol().split("_")[1]);//Meterlo en helper
		userLoginDetails.setEmail(user.getEmail());
		
		return userLoginDetails;
	}
	
	private User buildUser(com.artsoft.scb.model.entity.User user, List<GrantedAuthority> authorities){
		return new User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities (UserType userType){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority(userType.getRol()));
		return new ArrayList<GrantedAuthority>(auths);
	}
}

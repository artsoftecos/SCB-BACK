package com.artsoft.scb.controller.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/login","/applicant", "/offerer", "/documentType/", "/applicant/welcome/*", "/offerer/approve/*", "/offerer/reject/*", "/applicantDocument", "/offerer/approved", "/offerer/pending", 
				"/offerer/rejected","/convocatoryType","/convocatory/*", "/phase/*", "/convocatory/getByOffererState/**", "/convocatory/getByPendingPhases/**",
				"/convocatory/create", "/convocatory/edit", "/convocatory/getByOfferer/**", "/convocatory/getById/**", "/convocatory/getPending/**",
				"/phase/edit", "/phase/delete/**", "/phase/getPhasesOfConvocatory/**", "/phase/getPhases/**", "/AppPerPhase/byId/**", "/phase", "/fieldType",
				"/fieldType/Validations/**", "/applicant/postulations/**","/applicant/places/**","/applicant/notAppliedConvocatories/**").permitAll()
		.anyRequest()
			.fullyAuthenticated()
		.and()
			.httpBasic()
		.and()
			.csrf()
			.disable();
	}

}

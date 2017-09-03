package com.artsoft.scb.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.artsoft.scb.controller.configuration.SecurityConfiguration;

@Import(SecurityConfiguration.class)
public class BaseControllerTest {

	@TestConfiguration
	static class ApplicantControllerTestContextConfiguration {

		@Bean("userService")
		public UserDetailsService userService() throws Exception {
			return new UserDetailsService() {

				@Override
				public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
	}

//	@SuppressWarnings("unused")
//	@Autowired
//	private MockMvc mvc;

//	@Autowired
//	private WebApplicationContext context;

//	@Before
//	public void setup() {
//		mvc = MockMvcBuilders// .standaloneSetup(new ApplicantController())
//				.webAppContextSetup(context).apply(springSecurity()).build();
//	}	
}

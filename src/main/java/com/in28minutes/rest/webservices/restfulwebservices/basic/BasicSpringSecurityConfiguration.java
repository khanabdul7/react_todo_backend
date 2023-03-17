package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicSpringSecurityConfiguration {

	// Filter chain
	
	
	
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(auth -> 
											auth
											.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // allowing for option requests, that are sent prior to auth requests
											.anyRequest().authenticated())   // authenticate all requests
				.httpBasic(Customizer.withDefaults())  // basic authentication
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless rest api
				.csrf().disable() // disabling csrf
				.build();
	}
}

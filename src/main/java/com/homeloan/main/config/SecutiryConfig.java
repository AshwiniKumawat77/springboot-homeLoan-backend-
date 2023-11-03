package com.homeloan.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {
	
	
	
	@Bean
	 static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
//		http.csrf(csrf->csrf.disable())
//    	.cors(cors ->cors.disable())
//    	.authorizeHttpRequests(auth -> 
//    			auth.antMatchers("/api/auth/**").permitAll()
//    			.antMatchers("/home/**").authenticated()
//    			.antMatchers(HttpMethod.GET).permitAll()
//    			.anyRequest().authenticated())
//    	.exceptionHandling(ex -> ex.authenticationEntryPoint(null))
//    	.sessionManagement(session-> 
//    	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
		
				return null;
		
	}
}

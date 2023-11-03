package com.homeloan.main.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.main.exception.ErrorMessageException;
import com.homeloan.main.model.User;
import com.homeloan.main.model.role.Role;
import com.homeloan.main.model.role.RoleName;
import com.homeloan.main.payload.ApiResponse;
import com.homeloan.main.payload.JwtResponse;
import com.homeloan.main.payload.request.LoginRequest;
import com.homeloan.main.payload.request.SignUpRequest;
import com.homeloan.main.repository.RoleRepository;
import com.homeloan.main.repository.UserRepository;
import com.homeloan.main.security.JwtTokenProvider;
import com.homeloan.main.util.Mapper;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	@PostMapping("/auth/signin")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest login){
		log.info("AuthController:: login request body {} ", Mapper.mapToJsonString(login));
			Authentication authentication = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								login.getUsernameOrEmail(),
								login.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String token = jwtTokenProvider.generateToken(authentication);
			log.info("customer signin ");
			return ResponseEntity.ok(new JwtResponse(token));

	}
	
	 
	 @PostMapping("/signup")
	    public ResponseEntity<ApiResponse>  Singup(@RequestBody SignUpRequest signUpRequest) {
	    	 if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
				throw new ErrorMessageException("Username is already taken");			}
	    	 if(Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
	    		 throw new ErrorMessageException("Email is already taken");
	    	 }
	    	 
	    	 User user = new User(signUpRequest.getFirstName(),
	    			 signUpRequest.getLastName(),
	    			 signUpRequest.getUsername(),
	    			 signUpRequest.getEmail(),
	    			 passwordEncoder.encode(signUpRequest.getPassword()));
	    	
	    	 List<Role> roles = new ArrayList<>();
	    	
	     	
	   if(userRepository.count()==0) {
		  
		 roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
				 .orElseThrow(()-> new ErrorMessageException("User role not set")));
		 log.info("AuthController:: User role set");
		 roles.add(roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new ErrorMessageException("User role not set")));
		} else {
			roles.add(roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new ErrorMessageException("User role not set")));
			log.warn("AuthController:: User role not set");
		}
	   
	   user.setRoles(roles);
	   log.info("AuthController:: user role set successfully ");
	   userRepository.save(user);
	   
	   log.info("AuthController:: customer signup successfully ");
	    	 return ResponseEntity.ok().body(new ApiResponse(Boolean.TRUE,"User registered successfully"));	
	    }
	
}

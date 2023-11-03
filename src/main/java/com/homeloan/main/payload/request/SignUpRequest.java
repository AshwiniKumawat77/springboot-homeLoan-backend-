package com.homeloan.main.payload.request;

import lombok.Data;

@Data
public class SignUpRequest {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
}

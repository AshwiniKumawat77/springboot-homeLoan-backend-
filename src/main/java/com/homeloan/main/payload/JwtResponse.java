package com.homeloan.main.payload;

import lombok.Data;

@Data
public class JwtResponse {
	private  String accessToken;
	private String tokenType = "Bearer";
	
	public JwtResponse(String token) {
		this.accessToken= token;
	}

}

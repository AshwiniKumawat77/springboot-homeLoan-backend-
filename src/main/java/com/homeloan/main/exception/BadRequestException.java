package com.homeloan.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.homeloan.main.payload.ApiResponse;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	
	private ApiResponse apiResponse;
	
	public BadRequestException(String msg) {
		super(msg);
	}
	
	public BadRequestException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}
	
	public BadRequestException(ApiResponse apiResponse,String msg) {
		super(msg);
		this.apiResponse=apiResponse;
	}

}

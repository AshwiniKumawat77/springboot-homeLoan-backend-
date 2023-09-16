package com.homeloan.main.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homeloan.main.payload.ApiResponse;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public ResponseEntity< ApiResponse> resolveException(BadRequestException exception){
		ApiResponse apiResponse = exception.getApiResponse();
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,List<String>> handleValidationErrors(MethodArgumentNotValidException ex){
		
		Map<String,List<String>> error = new HashMap<>();
		
		 List<String> errors = ex.getBindingResult().getFieldErrors()
	                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		 	error.put("errors", errors);

		return error;
	}
}

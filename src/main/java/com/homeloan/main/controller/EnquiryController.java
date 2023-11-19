package com.homeloan.main.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.payload.ApiResponse;
import com.homeloan.main.payload.EnquiryResponse;
import com.homeloan.main.payload.PaginatedResponse;
import com.homeloan.main.payload.PaginationRequest;
import com.homeloan.main.payload.request.EnquiryRequest;
import com.homeloan.main.service.EnquiryService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Hp
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/enquiry")
@Slf4j
public class EnquiryController {
	
	@Autowired
	private EnquiryService enquiryService;
	
		
	/**
	 * Create a new UserEnquiry. 
	 * 
	 * @param enquiryRequest  
	 * @return
	 */
	@PostMapping
	public ResponseEntity<EnquiryRequest> addEnquiry(@Valid @RequestBody EnquiryRequest enquiryRequest){
		log.info("EnquiryController:: add new Enquriy", enquiryRequest);
		 EnquiryRequest addEnquiry = enquiryService.addEnquiry(enquiryRequest);
		 log.info("EnquiryController:: Enquriy add successfully", enquiryRequest);
		return new ResponseEntity<EnquiryRequest>(addEnquiry,HttpStatus.CREATED);
		
	}
	

		
	
	/**
	 * get All Enquiry
	 * @param request
	 * @return
	 */
	@PostMapping("/enquiries")
	public ResponseEntity<PaginatedResponse<EnquiryResponse>> getAllEnquiryByPaging(
			@RequestBody PaginationRequest request){
		log.info("EnquiryController:: get All Enquries");
		PaginatedResponse<EnquiryResponse> response = enquiryService.findAllEnquiry(request);
	
		return ResponseEntity.ok(response);
	
	}
	
	
	/**
	 * Retrieves a enquiry by cibistatus
	 * @param request
	 * @param cibilstatus
	 * @return
	 */
	@PostMapping("/enquiries/{cibilstatus}")
	public ResponseEntity<PaginatedResponse<EnquiryResponse>> getEnquiryByCibilStatus(
			@RequestBody PaginationRequest request,
			@PathVariable CibilStatus cibilstatus){
		PaginatedResponse<EnquiryResponse> response = enquiryService.getEnquiryByCibilStatus(cibilstatus, request);
		log.info("EnquiryController:: get All Enquries by cibil status successfully");
		return new ResponseEntity<PaginatedResponse<EnquiryResponse>>(response,HttpStatus.OK);
		
	}
	
		
	/**
	 * Update enquiry
	 * @param enquiryRequest
	 * @param id
	 * @return  
	 */
	@PutMapping("/enq/{id}")
	public ResponseEntity<EnquiryRequest> updateEnquiry(@Valid @RequestBody EnquiryRequest enquiryRequest,
			@PathVariable("id") Integer id){
		log.info("EnquiryController:: get  Enquriy by id");
		return new ResponseEntity<EnquiryRequest>(enquiryService.updateEnquiry(enquiryRequest,id),HttpStatus.CREATED);
		
	}
	
	/**
	 * delete enquiry
	 * @param id
	 * @return message of delete enquiry
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteEnquiry(@PathVariable Integer id){
		enquiryService.deleteEnquiry(id);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(Boolean.TRUE,"Enquiry deleted successfully"), HttpStatus.OK);
		
	}
}

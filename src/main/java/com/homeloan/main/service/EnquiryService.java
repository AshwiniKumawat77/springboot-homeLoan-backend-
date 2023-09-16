package com.homeloan.main.service;

import javax.validation.Valid;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.payload.EnquiryResponse;
import com.homeloan.main.payload.PaginatedResponse;
import com.homeloan.main.payload.PaginationRequest;
import com.homeloan.main.payload.request.EnquiryRequest;

public interface EnquiryService {

	EnquiryRequest addEnquiry(@Valid EnquiryRequest enquiryRequest);

	
	void deleteEnquiry(Integer id);

	EnquiryRequest updateEnquiry(@Valid EnquiryRequest enquiryRequest, Integer id);

	PaginatedResponse<EnquiryResponse> findAllEnquiry(PaginationRequest request);

	PaginatedResponse<EnquiryResponse> getEnquiryByCibilStatus(CibilStatus cibilstatus, PaginationRequest request);

	
	
}

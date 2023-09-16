package com.homeloan.main.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.exception.BadRequestException;
import com.homeloan.main.model.UserEnquiry;
import com.homeloan.main.payload.ApiResponse;
import com.homeloan.main.payload.EnquiryResponse;
import com.homeloan.main.payload.PaginatedResponse;
import com.homeloan.main.payload.PaginationRequest;
import com.homeloan.main.payload.request.EnquiryRequest;
import com.homeloan.main.repository.EnquiryRespository;
import com.homeloan.main.service.EnquiryService;

/**
 * @author Hp
 *
 */
@Service
public class EnquiryServiceImpl implements EnquiryService{
	@Autowired
	private EnquiryRespository enquiryRespository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	@Override
	public EnquiryRequest addEnquiry(@Valid EnquiryRequest enquiryRequest) {
		UserEnquiry enquiry = modelMapper.map(enquiryRequest, UserEnquiry.class); 
		enquiry.setCibilStatus(CibilStatus.pending);
		
		if(enquiryRespository.existsByUserEmailId(enquiry.getUserEmailId())) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE,"Email Id is already taken");
			throw new BadRequestException(apiResponse);
		}
		
		if(enquiryRespository.existsByPancardNumber(enquiry.getPancardNumber())) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE,"Pan no is already taken");
			throw new BadRequestException(apiResponse);
		}
		UserEnquiry addEnq = enquiryRespository.save(enquiry);
		return modelMapper.map(addEnq, EnquiryRequest.class);
		 	 
	}

	

	@Override
	public PaginatedResponse<EnquiryResponse> findAllEnquiry(PaginationRequest request) {
		
		int pageNuber = request.getPageNumber();
		int pageSize = request.getPageSize();
		
		if(pageNuber <= 0) {
			pageNuber = 1;
		}
		if(pageSize <= 0 ) {
			pageSize = 10;
		}
		Pageable pageable = PageRequest.of(pageNuber-1,pageSize);
		Page<UserEnquiry> page = enquiryRespository.findAll(pageable);
		List<EnquiryResponse> enquiries = page.getContent()
			.stream().map(enquiry -> modelMapper.map(enquiry, EnquiryResponse.class))
			.collect(Collectors.toList());
	
		return new PaginatedResponse<>(
				enquiries,
				pageNuber,
				pageSize,
				page.getTotalElements(),
				page.getTotalPages()
				);
	}

	

	@Override
	public void deleteEnquiry(Integer id) {
		 UserEnquiry enquiry = enquiryRespository.findById(id)
		 .orElseThrow(() -> new BadRequestException(id+"Id is not present"));
		 
		 enquiryRespository.delete(enquiry);
		
	}

	@Override
	public EnquiryRequest updateEnquiry(@Valid EnquiryRequest enquiryRequest, Integer id) {
		UserEnquiry enquiry = enquiryRespository.findById(id)
		.orElseThrow(() -> new BadRequestException("id is not found"));
		
		if(enquiryRequest==null) {
			throw new BadRequestException("");
		}
		else {
			enquiry.setUserFirstName(enquiryRequest.getUserFirstName());
			enquiry.setUserLastName(enquiryRequest.getUserLastName());
			enquiry.setUserContactNumber(enquiryRequest.getUserContactNumber());
			enquiry.setUserEmailId(enquiryRequest.getUserEmailId());
			enquiry.setPancardNumber(enquiryRequest.getPancardNumber());
			UserEnquiry userEnquiry = enquiryRespository.save(enquiry);
			return modelMapper.map(userEnquiry, EnquiryRequest.class);
		}
		
	}



	@Override
	public PaginatedResponse<EnquiryResponse> getEnquiryByCibilStatus(CibilStatus cibilstatus, PaginationRequest request) {
		int pageNuber = request.getPageNumber();
		int pageSize = request.getPageSize();
		
		if(pageNuber <= 0) {
			pageNuber = 0;
		}
		if(pageSize <= 0 ) {
			pageSize = 10;
		}
		
		Pageable pageable = PageRequest.of(pageNuber, pageSize);
		
		 Page<UserEnquiry> page = enquiryRespository.findByCibilStatus(cibilstatus,pageable);
		List<EnquiryResponse> enquiries = page.getContent()
				.stream().map(enquiry -> modelMapper.map(enquiry, EnquiryResponse.class))
				.collect(Collectors.toList());
				
			    //return new PageImpl<>(responseList,page,pagelist.getTotalElements());
			    
		return new PaginatedResponse<>(
				enquiries,
				pageNuber,
				pageSize,
				page.getTotalElements(),
				page.getTotalPages()
				);
			
	}

	


}

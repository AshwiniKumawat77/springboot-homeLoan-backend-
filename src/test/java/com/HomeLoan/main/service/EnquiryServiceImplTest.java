package com.HomeLoan.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.exception.BadRequestException;
import com.homeloan.main.model.UserEnquiry;
import com.homeloan.main.payload.EnquiryResponse;
import com.homeloan.main.payload.PaginatedResponse;
import com.homeloan.main.payload.PaginationRequest;
import com.homeloan.main.payload.request.EnquiryRequest;
import com.homeloan.main.repository.EnquiryRepository;
import com.homeloan.main.serviceImpl.EnquiryServiceImpl;

public class EnquiryServiceImplTest {
	
	@InjectMocks
	private  EnquiryServiceImpl enquiryService;
	
	@Mock
	private EnquiryRepository enquiryRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	//private Validator validator;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		// Create a Validator instance for validation testing
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
		
	}
	
	 private EnquiryRequest enquiryRequestStub() {
			
			EnquiryRequest enquiryRequest = new EnquiryRequest();
			enquiryRequest.setEnquiryId(1);
			enquiryRequest.setUserFirstName("Ashwini");
			enquiryRequest.setUserLastName("Kumawat");
			enquiryRequest.setUserEmailId("ashwini@gmail.com");
			enquiryRequest.setUserContactNumber("7798751998");
			enquiryRequest.setPancardNumber("AAAAA1234D");
			enquiryRequest.setCibilStatus(CibilStatus.pending);
	       return enquiryRequest;
	}
 
	
	@Test
    public void testAddEnquiry_Success() {
        // Create a sample EnquiryRequest
		EnquiryRequest enquiryRequest = enquiryRequestStub();
       

        UserEnquiry userEnquiry = new UserEnquiry();
        userEnquiry.setUserEmailId("ashwini@gmail.com");
		userEnquiry.setUserContactNumber("7798751998");
		userEnquiry.setPancardNumber("AAAAA1234D");
		

		 // Mock the modelMapper.map method
        when(modelMapper.map(enquiryRequest, UserEnquiry.class)).thenReturn(userEnquiry);
        
        // Mock the repository methods
        when(enquiryRepository.existsByUserEmailId(anyString())).thenReturn(false);
        when(enquiryRepository.existsByPancardNumber(anyString())).thenReturn(false);
             
        when(enquiryRepository.save(userEnquiry)).thenReturn(userEnquiry);

        when(modelMapper.map(userEnquiry, EnquiryRequest.class)).thenReturn(enquiryRequest);

        
        // Call the service method
        EnquiryRequest result = enquiryService.addEnquiry(enquiryRequest);
        
        

        // Assertions
        assertNotNull(result);
        assertEquals(userEnquiry.getUserEmailId(), result.getUserEmailId());
        assertEquals(userEnquiry.getPancardNumber(), result.getPancardNumber());
        assertEquals(CibilStatus.pending, result.getCibilStatus());

        // Verify that the repository methods were called
        verify(enquiryRepository, times(1)).existsByUserEmailId(enquiryRequest.getUserEmailId());
        verify(enquiryRepository, times(1)).existsByPancardNumber(enquiryRequest.getPancardNumber());
        verify(enquiryRepository, times(1)).save(userEnquiry);
    }

    @Test
    void testAddEnquiry_DuplicateEmail() {
    	 // Create a sample EnquiryRequest
        EnquiryRequest request = enquiryRequestStub();
       
        UserEnquiry userEnquiry = new UserEnquiry();
        userEnquiry.setEnquiryId(1);
        userEnquiry.setUserFirstName("Ashwini");
		userEnquiry.setUserLastName("Kumawat");
		userEnquiry.setUserEmailId("ashwini@gmail.com");
		userEnquiry.setUserContactNumber("7798751998");
		userEnquiry.setPancardNumber("AAAAA1234D");
		userEnquiry.setCibilStatus(CibilStatus.pending);

		 // Mock the modelMapper.map method
        when(modelMapper.map(request, UserEnquiry.class)).thenReturn(userEnquiry);
        
        // Mock the repository methods

        
        // Mock the repository to return true for email check (already taken)
        when(enquiryRepository.existsByUserEmailId("ashwini@gmail.com")).thenReturn(true);
        
        // Call the service method and expect a BadRequestException
        BadRequestException exception = assertThrows(BadRequestException.class, () -> enquiryService.addEnquiry(request));

        // Verify the exception message
        assertEquals("Email Id  is already taken", exception.getMessage());

        // Verify that the repository method was called
        verify(enquiryRepository, times(1)).existsByUserEmailId(request.getUserEmailId());
}

    @Test
    void testAddEnquiry_DuplicatePancard() {
        // Create a sample EnquiryRequest
        EnquiryRequest enquiryRequest = enquiryRequestStub();
        
        UserEnquiry userEnquiry = new UserEnquiry();
       	userEnquiry.setPancardNumber("AAAAA1234D");
        
        // Mock the modelMapper.map method
        when(modelMapper.map(enquiryRequest, UserEnquiry.class)).thenReturn(userEnquiry);

        // Mock the repository method to return true (pan number already exists)
        when(enquiryRepository.existsByPancardNumber("AAAAA1234D")).thenReturn(true);

        // Verify that a BadRequestException is thrown
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            enquiryService.addEnquiry(enquiryRequest);
        });

        // Verify that the exception message is as expected
        assertEquals("Pan no is already taken", exception.getMessage());
    }

	

    @Test
    public void testFindAllEnquiry_Success() {
        // Create a sample PaginationRequest
        PaginationRequest request = new PaginationRequest();
        request.setPageNumber(1);
        request.setPageSize(10);

        // Create a list of UserEnquiry objects for the Page
        List<UserEnquiry> userEnquiries = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            UserEnquiry enquiry = new UserEnquiry();
            enquiry.setUserEmailId("test" + i + "@example.com");
            userEnquiries.add(enquiry);
        }

        // Create a Page object with UserEnquiries
        Page<UserEnquiry> page = new PageImpl<>(userEnquiries);

        // Mock the repository to return the Page
        
        when(enquiryRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        // Mock the ModelMapper to map UserEnquiries to EnquiryResponse
       
        
           when(modelMapper.map(any(UserEnquiry.class), eq(EnquiryResponse.class)))
                .thenAnswer(invocation -> {
                    UserEnquiry userEnquiry = invocation.getArgument(0);
                    EnquiryResponse response = new EnquiryResponse();
                    response.setUserEmailId(userEnquiry.getUserEmailId());
                    return response;
                });

        // Call the service method
        PaginatedResponse<EnquiryResponse> result = enquiryService.findAllEnquiry(request);

        // Verify the result and interactions
         assertNotNull(result);
         assertEquals(1, result.getPageNumber());
         assertEquals(10, result.getPageSize());
         assertEquals(10, result.getTotalElements());
         assertEquals(1, result.getTotalPages());
         assertEquals(10, result.getContent().size());
     
        // Verify that the repository method was called with the correct Pageable
        verify(enquiryRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test 
    public void testdeleteEnquiry_Success() {
    	int id =1;
    	
    	UserEnquiry userEnquiry = new UserEnquiry();
    	
    	when(enquiryRepository.findById(id)).thenReturn(Optional.of(userEnquiry));
    	
    	enquiryService.deleteEnquiry(id);
    	
    	verify(enquiryRepository,times(1)).delete(userEnquiry);
    	
    	
    		
    }
    
    @Test
    public void testDeleteEnquiry_NonExistingId() {
        // Create a sample ID for a non-existing UserEnquiry
    	int id = 1;
    	
    	 // Mock the repository to return an empty Optional
    	when(enquiryRepository.findById(id)).thenReturn(Optional.empty());
    	
    	// Call the service method and expect a BadRequestException
    	BadRequestException badRequestException = assertThrows(BadRequestException.class, 
    			() -> enquiryService.deleteEnquiry(id));
    	
    	// Verify the exception message
    	assertEquals(id+" Id is not present", badRequestException.getMessage());
    	
    	 // Verify that the repository findById method was called
        verify(enquiryRepository, times(1)).findById(id);

        // Verify that the repository delete method was not called
        verify(enquiryRepository, never()) .delete(any());
     
    }

   
}

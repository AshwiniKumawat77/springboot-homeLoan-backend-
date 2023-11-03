package com.HomeLoan.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeloan.main.controller.EnquiryController;
import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.payload.ApiResponse;
import com.homeloan.main.payload.EnquiryResponse;
import com.homeloan.main.payload.PaginatedResponse;
import com.homeloan.main.payload.PaginationRequest;
import com.homeloan.main.payload.request.EnquiryRequest;
import com.homeloan.main.service.EnquiryService;



@WebMvcTest(controllers = EnquiryController.class)
public class EnquiryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnquiryService enquiryService;
	
	@InjectMocks
	private EnquiryController enquiryController;
	
	@Test
	public void testAddEnquiry() throws Exception{
		// Create a sample EnquiryRequest
		EnquiryRequest enquiryRequest = enquiryRequestStub();
		
		// Mock the behavior of enquiryService.addEnquiry
		when(enquiryService.addEnquiry(enquiryRequest))
		.thenReturn(enquiryRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		String enquiryJson = mapper.writeValueAsString(enquiryRequest);
		
		// Perform a POST request using MockMvc
		 MvcResult result = mockMvc.perform(post("/enquiry")
			.contentType(MediaType.APPLICATION_JSON)
		    .content(enquiryJson))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.firstname").value("Ashwini"))
			.andExpect(jsonPath("$.lastname").value("Kumawat"))
			.andExpect(jsonPath("$.emailid").value("ashwini@gmail.com"))
			.andExpect(jsonPath("$.contactno").value("7798751998"))
			.andExpect(jsonPath("$.panno").value("AAAAA1234D"))
			.andReturn();
		 
		// Extract and deserialize the response
		 MockHttpServletResponse response = result.getResponse();
		 
		// Deserialize directly into EnquiryRequest
		 EnquiryRequest responseEnquiry = mapper.readValue(response.getContentAsString(), EnquiryRequest.class);
		 
		// Check if the response status code is HttpStatus.CREATED
		 assertEquals(HttpStatus.CREATED.value(), response.getStatus());
           
		//verify(enquiryService, times(1)).addEnquiry(any(EnquiryRequest.class));
		// Check if the response body matches the expected EnquiryRequest
		 assertEquals(enquiryService.addEnquiry(enquiryRequest), responseEnquiry);
	}

    	
	@Test
	public void testGetAllEnquiryByPaging() throws Exception {
		//PaginationRequest request = paginationRequestStub();
		PaginationRequest request = new PaginationRequest();
		PaginatedResponse<EnquiryResponse> paginatedResponse = new PaginatedResponse<>();
		//		paginatedResponse.setContent(Arrays.asList(enquiryResponseStub()));
		
		when(enquiryService.findAllEnquiry(request)).thenReturn(paginatedResponse);
		
		MvcResult mvcResult = mockMvc.perform(post("/enquiry/enquiries")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();
		
		
          String response = mvcResult.getResponse().getContentAsString();
         ObjectMapper mapper = new ObjectMapper();
        PaginatedResponse<EnquiryResponse> responseEntity = mapper.readValue(response,
                        new TypeReference<PaginatedResponse<EnquiryResponse>>() {});


				assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
				assertEquals(paginatedResponse, responseEntity);
		
	}
	

	@Test 
	public void testUpdateEnquiry() throws Exception {
		// Create a sample EnquiryRequest
		EnquiryRequest enquiryRequest = enquiryRequestStub();
		// Create a sample EnquiryRequest response
		EnquiryRequest responsEnquiryRequest = enquiryRequestStub();
		
		// Create a sample ID
		int id = 1;
		
		// Mock the behavior of enquiryService.updateEnquiry
		when(enquiryService.updateEnquiry(enquiryRequest, id)).thenReturn(responsEnquiryRequest);
		
		// Perform a PUT request using MockMvc
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.put("/enquiry/enq/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(enquiryRequest)))
				.andExpect(status().isCreated())
				.andReturn();
		
		// Extract and deserialize the response
		MockHttpServletResponse response = mvcResult.getResponse();
		
		// Deserialize directly into EnquiryRequest
		ObjectMapper mapper = new ObjectMapper();
		EnquiryRequest responseDto = mapper.readValue(response.getContentAsString(), EnquiryRequest.class);
		
		// Check if the response status code is HttpStatus.CREATED
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		
		// Check if the response body matches the expected EnquiryRequest
		assertEquals(responsEnquiryRequest, responseDto);
	}
	
	
	@Test
	public void testdeleteEnquiry() throws Exception {
		// Create a sample ID
		int id = 1;
		// Mock the behavior of enquiryService.deleteEnquiry
		doNothing().when(enquiryService).deleteEnquiry(id);
		// Perform a DELETE request using MockMvc
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.delete("/enquiry/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// Extract and deserialize the response
		MockHttpServletResponse response = mvcResult.getResponse();
		
		// Deserialize directly into ApiResponse
		ObjectMapper mapper= new ObjectMapper();
		ApiResponse apiResponse = mapper.readValue(response.getContentAsString(), ApiResponse.class);
	
		// Check if the response status code is HttpStatus.OK
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		
		// Check if the response body matches the expected ApiResponse
		assertEquals(new ApiResponse(Boolean.TRUE, "Enquiry deleted successfully"), apiResponse);
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
	       
	       private EnquiryResponse enquiryResponseStub() {
	   		
				EnquiryResponse enquiryResponse = new EnquiryResponse();
				enquiryResponse.setEnquiryId(1);
				enquiryResponse.setUserFirstName("Ashwini");
				enquiryResponse.setUserLastName("Kumawat");
				enquiryResponse.setUserEmailId("ashwini@gmail.com");
				enquiryResponse.setUserContactNumber("7798751998");
				enquiryResponse.setPancardNumber("AAAAA1234D");
				//enquiryRequest.setCibilStatus(CibilStatus.pending);
		       return enquiryResponse;
		}
	       private PaginationRequest paginationRequestStub() {
	    	   PaginationRequest paginationRequest = new PaginationRequest();
	    	   paginationRequest.setPageNumber(1);
	    	   paginationRequest.setPageSize(5);
	    	   return paginationRequest;
	       }
}

package com.homeloan.main.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.homeloan.main.enums.CibilStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryRequest {
	
	@JsonProperty(value = "id")
	@NotNull
	private int enquiryId;
	
	@JsonProperty(value = "firstname")
	@NotNull(message = "Invalid Name: First Name is NULL")
	@Size(min=3,max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
	private String userFirstName;
	
	@JsonProperty(value = "lastname")
	@NotNull(message = "Invalid Name: Last Name is NULL")
	@Size(min=3,max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
	private String userLastName;
	
	@JsonProperty(value = "emailid")
	@NotNull(message = "Invalid EmailNo: email is NULL")
	@Email(message = "Enter valid EmailId")
	private String userEmailId;
	
	@JsonProperty(value = "contactno")
	@NotNull( message = "Invalid Phone number:Mobail Number is NULL")
	@Pattern(regexp = "[7-9]{1}[0-9]{9}",message = "Enter valid mobile no" )
	private String userContactNumber;
	
	@JsonProperty(value = "panno")
	@NotNull(message = "Invalid Phone number: pan no is NULL")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Enter valid Pan Number")
	private String pancardNumber;
	
	@JsonProperty(value = "cibilStatus")
	private CibilStatus cibilStatus;
	
	@JsonIgnore
	@JsonProperty(value = "cibilScore")
	private Integer cibilScore;
	

}

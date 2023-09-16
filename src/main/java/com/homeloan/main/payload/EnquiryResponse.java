package com.homeloan.main.payload;

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
public class EnquiryResponse {
	@JsonProperty(value = "id")
	private int enquiryId;
	
	@JsonProperty(value = "firstname")
	private String userFirstName;
	@JsonProperty(value = "lastname")
	private String userLastName;
	@JsonProperty(value = "emailid")
	private String userEmailId;
	@JsonProperty(value = "contactno")
	private String userContactNumber;
	@JsonProperty(value = "panno")
	private String pancardNumber;
	@JsonProperty(value = "cibilStatus")
	private CibilStatus cibilStatus;
	@JsonProperty(value = "cibilScore")
	private Integer cibilScore;

}

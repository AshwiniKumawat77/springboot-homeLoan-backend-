package com.homeloan.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.homeloan.main.enums.CibilStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="Usr_Enquiry", 
uniqueConstraints = {
		@UniqueConstraint(columnNames = {"emailid"}),
		@UniqueConstraint(columnNames = { "panno" })})
public class UserEnquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int enquiryId;
	
	
	@Column( nullable = false,name = "first_name")
	private String userFirstName;
	
	@Column(name = "last_name", nullable = false)
	private String userLastName;
	
	@Column(name = "emailid", nullable = false)
	@NaturalId
	@Email
	private String userEmailId;
	
	@Column(name = "contactno", nullable = false)
	@Pattern(regexp = "[7-9]{1}[0-9]{9}")
	private String userContactNumber;
	
	@Column(name = "panno", nullable = false)
	private String pancardNumber;
	
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "cibilStatus", nullable = false)
	
	private CibilStatus cibilStatus;
	@Column(name = "cibilScore", nullable = true)
	private Integer cibilScore;
}

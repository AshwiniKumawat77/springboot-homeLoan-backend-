package com.homeloan.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PersonalDetails")
public class UserPersonalDetails extends DateAudit{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
		
	@Column(name = "dob")
	private String userDateOfBirth;
	
	@Column(name = "panNo", nullable = false)
	@Pattern(regexp = "([A-Za-z])[5}[0-9]{4}[A-Za-z}{1}")
	private String userPanNo;

	@Column(name = "contactno", nullable = false)
	@Pattern(regexp = "[7-9]{1}[0-9]{9}")
	private long   usermobileNo;
	
	@Column(name = "emailid", nullable = false)
	@NaturalId
	@Email
	private String userEmail;
	
	@Column(name = "gender")
	private String userGender; 
	
	@Column(name = "adharNo")
	private long useradhaarNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserAddress address;
	

}

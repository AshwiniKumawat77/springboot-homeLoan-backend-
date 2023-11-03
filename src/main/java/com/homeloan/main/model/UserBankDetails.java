package com.homeloan.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties
@Table(name = "Bank_Datails")
public class UserBankDetails extends DateAudit {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankId;
	
	@NotBlank
	@Column(name = "account_no")
	private Long  bankAccountNo;
	@NotBlank
	@Column(name = "bank_name ")
	private String bankName;
	
	@Column(name = "branch_name")
	private String bankBranchName;
	
	@Column(name = "ifsc_no")
	private String bankIfscNo;
	
	@Column(name = "account_type")
	private String accountType;
	
	// credit card details secondary class
}

package com.homeloan.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanDisbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer agreementId;
	private Double totalLoanSanctionedAmount;
	private Double amountwithIntrest;
	private String amountPaidDate;
	private String paymentStatus;
	private Long dealerBankAccountNumber;
	private String builderBankName;
	private String builderBankIfscNumber;
	
	

}

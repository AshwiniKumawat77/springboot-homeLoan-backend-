package com.homeloan.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBuilderDetails extends DateAudit {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String builderName;
	
	@Column(name = "account_no")
	private long builderAccountNo;
	
	@Column(name = "bank_name")
	private String builderBankName;
	
	@Column(name = "branch_Name")
	private String branchName;
	
	@Column(name = "ifsc_no")
	private String bankIfscNo;
	
	
}

package com.homeloan.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Document_details")
public class UserDocumentsDetails extends DateAudit{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int documentId;
	@Lob
	private byte[] pancard;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] adharcard;
	@Lob
	private byte[] salaryslip;
	@Lob
	private byte[] banksmt;
	@Lob
	private byte[] addproof;
}

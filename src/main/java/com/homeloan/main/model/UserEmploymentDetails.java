package com.homeloan.main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employment_Details")
public class UserEmploymentDetails extends DateAudit{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int empId;
	
	private String occupation;
	private double salary;
	private String employmentStatus;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<UserGuarantorDetails> guarantorDetails;
}

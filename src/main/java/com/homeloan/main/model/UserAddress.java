package com.homeloan.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.homeloan.main.model.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Address")
public class UserAddress extends DateAudit {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addId;
	
	@Column(name = "houseNo")
	private String houseNo;
	
	@Column(name = "landmark")
	@Size(max = 40)
	private String landmark;
	
	@Size(max = 40)
	@Column(name = "city")
	private String city;
	
	@Size(max = 40)
	@Column(name = "district")
	private String district;
	
	@Column(name = "pincode")
	@Size(max = 6)
	@Pattern(regexp = "[1-9]{1}[0-9]{5}")
	private long pincode;
	
	@Column(name = "state")
	@Size(max = 40)
	private String state;
	
	@Size(max = 20)
	private String country;

}

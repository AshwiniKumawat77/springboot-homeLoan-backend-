  package com.homeloan.main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.homeloan.main.model.audit.DateAudit;
import com.homeloan.main.model.role.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = { "username" }),
			//@UniqueConstraint(columnNames = { "email" })
		})
public class User extends DateAudit{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "first_name")
	@Size(max = 40)
	@Pattern(regexp = "([A-za-z])")
	private String firstName;
	
	
	@NotBlank
	@Column(name = "midle_name")
	@Size(max = 40)
	@Pattern(regexp = "([A-za-z])")
	private String midleName;
	
	@NotBlank
	@Column(name = "last_name")
	@Size(max = 40)
	@Pattern(regexp = "([A-za-z])")
	private String lastName;
	
	@Column(name = "emailid", nullable = false)
	@NaturalId
	@Email
	private String email;
	
	@NotBlank
	@Column(name = "username")
	@Size(max = 15)
	private String username;
	
	
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(max = 100)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
				joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id"))
	private List<Role> roles;	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserPersonalDetails personalDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserEmploymentDetails employmentDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserLoanDetails loanDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PropertyDetails propertyDetails;
	
	public User(String firstName, String lastName, String username, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
}

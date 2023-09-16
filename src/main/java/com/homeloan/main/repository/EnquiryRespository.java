package com.homeloan.main.repository;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.model.UserEnquiry;


@Repository
public interface EnquiryRespository extends JpaRepository<UserEnquiry, Integer>{

	boolean existsByUserEmailId(@NotBlank String userEmailId);

	boolean existsByPancardNumber(@NotBlank String pancardNumber);

	Page<UserEnquiry> findByCibilStatus(CibilStatus cibilstatus, Pageable pageable);

	
	
	

}

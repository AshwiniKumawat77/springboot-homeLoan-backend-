package com.HomeLoan.main.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.homeloan.main.enums.CibilStatus;
import com.homeloan.main.model.UserEnquiry;
import com.homeloan.main.repository.EnquiryRepository;

@ContextConfiguration
@DataJpaTest
public class EnquiryRepositoryTest {
	
	@Autowired
	private EnquiryRepository enquiryRepository;
	
	private UserEnquiry userEnquiry;
	
	@BeforeEach
	public void setUp() {
		userEnquiry = UserEnquiry.builder()
				.userFirstName("Ashwini")
				.userLastName("kumawat")
				.userEmailId("ashwini@gmail.com")
				.userContactNumber("7798751998")
				.pancardNumber("AAAAA1234D")
				.cibilStatus(CibilStatus.pending)
				.build();
				
	}

	@Test 
	public void should_save_enquiry() {
		
		 UserEnquiry enquiry = UserEnquiry.builder()
				.userFirstName("Ashwini")
				.userLastName("kumawat")
				.userEmailId("ashwini@gmail.com")
				.userContactNumber("7798751998")
				.pancardNumber("AAAAA1234D")
				.cibilStatus(CibilStatus.pending)
				.build();
		
		 UserEnquiry saveEnquiry = enquiryRepository.save(enquiry);
		
		assertThat(saveEnquiry).isNotNull();
		assertThat(saveEnquiry.getEnquiryId()).isGreaterThan(0);
	}
	
	
	@Test
//    public void testExistsByUserEmailId_WhenEmailExists_ShouldReturnTrue() {
//        // Arrange
//		
//       // String existingEmail = "existing@example.com";
//        UserEnquiry enquiryRequest = new UserEnquiry();
//		enquiryRequest.setEnquiryId(1);
//		enquiryRequest.setUserFirstName("Ashwini");
//		enquiryRequest.setUserLastName("Kumawat");
//		enquiryRequest.setUserEmailId("ashwini@gmail.com");
//		enquiryRequest.setUserContactNumber("7798751998");
//		enquiryRequest.setPancardNumber("AAAAA1234D");
//		enquiryRequest.setCibilStatus(CibilStatus.pending);
//    
//        // Mock the behavior of the repository method
//        
//        when(enquiryRepository.existsByUserEmailId(eq(existingEmail))).thenReturn(true);
//
//        // Act
//        boolean exists = enquiryRepository.existsByUserEmailId(existingEmail);
//
//        // Assert
//        assertTrue(exists);
//    }
//	


	
	 private UserEnquiry createTestData() {

	        List<UserEnquiry> testData = new ArrayList<>();
	        // Populate testData with UserEnquiry instances for testing
	        
	        UserEnquiry enquiryRequest = new UserEnquiry();
			enquiryRequest.setEnquiryId(1);
			enquiryRequest.setUserFirstName("Ashwini");
			enquiryRequest.setUserLastName("Kumawat");
			enquiryRequest.setUserEmailId("ashwini@gmail.com");
			enquiryRequest.setUserContactNumber("7798751998");
			enquiryRequest.setPancardNumber("AAAAA1234D");
			enquiryRequest.setCibilStatus(CibilStatus.pending);
	    
			UserEnquiry enquiryRequest1 = new UserEnquiry();
			enquiryRequest1.setEnquiryId(1);
			enquiryRequest1.setUserFirstName("Pavan");
			enquiryRequest1.setUserLastName("Kumawat");
			enquiryRequest1.setUserEmailId("pavan@gmail.com");
			enquiryRequest1.setUserContactNumber("7057296281");
			enquiryRequest1.setPancardNumber("PPPPP1234D");
			enquiryRequest1.setCibilStatus(CibilStatus.approved);
	    
			UserEnquiry enquiryRequest2 = new UserEnquiry();
			enquiryRequest2.setEnquiryId(1);
			enquiryRequest2.setUserFirstName("priyanka");
			enquiryRequest2.setUserLastName("amal;e");
			enquiryRequest2.setUserEmailId("priyanka@gmail.com");
			enquiryRequest2.setUserContactNumber("78963541336");
			enquiryRequest2.setPancardNumber("PRPRP1234D");
			enquiryRequest2.setCibilStatus(CibilStatus.pending);
	    
			testData.add(enquiryRequest);
			testData.add(enquiryRequest1);
			testData.add(enquiryRequest2);
	        return enquiryRequest;
	    }
}

package com.exercise.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;


import com.exercise.customer.Validation.CustomerValidation;
import com.exercise.customer.dao.CustomerRepository;
import com.exercise.customer.entity.customer;
import com.exercise.customer.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ServiceIntegrationTest {

	private final String CountryName= "Morocco" ; 
	private final String SecondCountryName= "Egypt" ; 
	
	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private CustomerValidation Validation ;
	
	@Test
	public void testAddContactHappyPath() {
	
       List<customer> customers = new ArrayList<>();		
       customers = customerservice.findAll();
       assertNotNull(customers
    		   );
	   assertEquals(customers.size() , 41);
		
	}	
    @Test
    public void GetCustomersByPhone() throws Exception { 	
	      String key = Validation.GetCountryCodeFromCountryName(CountryName);
          List<customer> CustomersList = customerservice.findByPhoneStartsWith(key); 
          assertNotNull(CustomersList);
          assertEquals(CustomersList.size(), 7);
          String Secondkey = Validation.GetCountryCodeFromCountryName(SecondCountryName);
          assertNull(Secondkey);
          
    }

}

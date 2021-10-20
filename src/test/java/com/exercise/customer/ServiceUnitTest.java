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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.exercise.customer.dao.CustomerRepository;
import com.exercise.customer.entity.customer;
import com.exercise.customer.service.CustomerServiceImplem; 

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ServiceUnitTest {
	
	@InjectMocks
	private CustomerServiceImplem service;
		
    @Mock
    CustomerRepository repo;
    
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void GetCustomers() throws Exception {
        List<customer> Customers = new ArrayList<>();
        customer customer = new customer();
        Customers.add(customer);
        Customers.add(customer);
        when(repo.findAll()).thenReturn(Customers);
        List<customer> result = service.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }
    @Test
    public void GetCustomersByPhone() throws Exception { 	         
        List<customer> Customers = new ArrayList<>();
        customer customer = new customer();
        Customers.add(customer);
        when(repo.findByPhoneStartsWith(any(String.class))).thenReturn(Customers);
        List<customer> result = service.findByPhoneStartsWith("(237)");
        assertNotNull(result);
        assertEquals(result.size(), 1);
            
    }


}

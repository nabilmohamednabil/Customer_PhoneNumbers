package com.exercise.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.customer.dao.CustomerRepository;
import com.exercise.customer.entity.customer;
import com.exercise.customer.service.CustomerService;
import com.exercise.customer.service.CustomerServiceImplem;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerApplicationTests {

	@Test
	void contextLoads() {
	}
	

    
}

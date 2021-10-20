package com.exercise.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.Model.*;
import static org.hamcrest.Matchers.*;
import com.exercise.customer.dao.CustomerRepository;
import com.exercise.customer.entity.customer;
import com.exercise.customer.service.*;
import com.exercise.customer.PresentationLayer.DataPresentation;
import com.exercise.customer.Validation.CustomerValidation;
import com.exercise.customer.WebController.*;
import com.exercise.customer.Creation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ControllerUnitTest {
	
	private final String Key = "Morocco" ;	
	private final String phone  = "(212) 698054317" ;	
	private final String keyword  = "keyword" ;
	private final String flag  = "flag" ;
	private final String flag2  = "flag2" ;
	
    private List<customer> Customers ;   
    private List<DataPresentation> Thecustomers = new ArrayList<>();
	
    @Mock
    CustomerService service;
    
    @Mock
    Model model ;
    
    CustomerController controller ;
    
    @Mock
    CustomerValidation validation ;
    
    @Mock
    create create ;
    
    @Mock
    CustomerRepository CustomerRepository;

    @BeforeEach
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this); 	
    	controller = new CustomerController(service  , create);
    	Customers = new ArrayList<>();
    	Customers.add(new customer ());
    	Customers.add(new customer ());
    	Customers.add(new customer ());
    	Customers.add(new customer ());
    	Thecustomers.add(new DataPresentation ());
    	Thecustomers.add(new DataPresentation ());
    	Thecustomers.add(new DataPresentation ());
    	Thecustomers.add(new DataPresentation ());
    }
    
	@Test
	public void ControllerUnitTest() throws Exception {
		String PageName = controller.listCustomers(model);
		assertEquals("Presentation", PageName);
		when(create.GetCountryCodeFromCountryName(any(String.class))).thenReturn(Key);		
		when(service.findByPhoneStartsWith(any(String.class))).thenReturn(Customers);	
		when(create.CreateStructure(any(List.class) , anyString())).thenReturn(Customers);
		
	   String PageName3 = controller.filterCustomers(model , Key , "on" , "on");
	 	assertEquals("Presentation", PageName3);
	 	
	   String PageName4 = controller.filterCustomers(model , Key , null , null);
		assertEquals("Presentation", PageName4);
	 	
		when(create.GetCountryCodeFromCountryName(any(String.class))).thenReturn(null);
		String PageName5 = controller.filterCustomers(model , Key , null , null);
	    assertEquals("Presentation", PageName5);
			
		List<DataPresentation> z = new ArrayList<>();
		z = create.CreateStructure( Customers , "ALL" );
        when(service.findAll()).thenReturn(Customers);
        List<customer> result = service.findAll();
        assertEquals(result.size(), 4);       
        assertEquals(z.size(), 4);	
	}
	
	@Test
	public void ControllerUnitTestFilter() throws Exception {		
        when(service.findByPhoneStartsWith(any(String.class))).thenReturn(Customers);
        List<customer> result = service.findByPhoneStartsWith(phone);
        assertNotNull(result);
        assertEquals(result.size(), 4);
	}
  	
	@Test
    public void TestFilterHappy() throws Exception {
		when(create.GetCountryCodeFromCountryName(any(String.class))).thenReturn(Key);		
		when(service.findByPhoneStartsWith(any(String.class))).thenReturn(Customers);	
		when(create.CreateStructure(any(List.class) , anyString())).thenReturn(Customers);
	    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/customers/filter?keyword=Uganda&flag=on&flag2=on"))
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attribute("FinalEnity", hasSize(4) ) ) ;
        
        mockMvc.perform(get("/customers/filter?keyword=Mozambique"))
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attribute("FinalEnity", hasSize(4) ) ) ;
        
        mockMvc.perform(get("/customers/filter?keyword=Ethiopia&flag=on"))
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attribute("FinalEnity", hasSize(4) ) ) ;
        
        mockMvc.perform(get("/customers/filter?keyword=Ethiopia&flag2=on"))
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attribute("FinalEnity", hasSize(4) ) ) ;
	}
	@Test
    public void TestFilterFail() throws Exception {
		when(create.GetCountryCodeFromCountryName(any(String.class))).thenReturn(null);		
		when(service.findByPhoneStartsWith(any(String.class))).thenReturn(null);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/customers/filter"))
        .andExpect(status().isOk())
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attributeDoesNotExist("FinalEnity") );
        mockMvc.perform(get("/customers/filter?keyword=EGYPT&flag=on&flag2=on"))
        .andExpect(status().isOk())
        .andExpect(status().isOk()).andExpect(view().name("Presentation"))
        .andExpect(model().attributeDoesNotExist("FinalEnity") );
	}
	
	@Test
    public void testMockMVC() throws Exception {
		
		when(service.findAll()).thenReturn(Customers);		
		when(create.CreateStructure(any(List.class) , anyString())).thenReturn(Thecustomers);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/customers/list/")).andExpect(status().isOk())
        .andExpect(view().name("Presentation"))    
        .andExpect(model().attributeExists("FinalEnity") )
        .andExpect(model().attribute("FinalEnity", hasSize(4) ) ) ;
       
    }
	
	
}

package com.exercise.customer.service;

 

import java.util.List;

import com.exercise.customer.dao.CustomerRepository;
import com.exercise.customer.entity.customer;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImplem implements CustomerService {

              private CustomerRepository CustomerRepository;  
              
              @Autowired
              public CustomerServiceImplem(CustomerRepository CustomerRepository) {
                             this.CustomerRepository = CustomerRepository;
              }
             
              @Override
              public List<customer> findAll() {                           
                                	  
					return StreamSupport
							  .stream(CustomerRepository.findAll().spliterator(), false)
							  .collect(Collectors.toList());
              }

              @Override
              public customer findById(int theId) {            
                           Optional<customer> result = CustomerRepository.findById(theId);                         
                           customer thecustomer = null;
                             if (result.isPresent()) {
                            	 thecustomer = result.get();
                             }
                             else {
                                           throw new RuntimeException("Did not find customer id - " + theId);
                             }
                             return thecustomer;
              }

              @Override
              public void save(customer cus) {

                  CustomerRepository.save(cus);
              }
              
              @Override
              public void deleteById(int theId) {
                             CustomerRepository.deleteById(theId);
              }

			  @Override
			  public List<customer> filter(String keyword) {							  
					return StreamSupport
							  .stream(CustomerRepository.filter(keyword).spliterator(), false)
							  .collect(Collectors.toList());
				}
			  
			  @Override
		 	  public List<customer> findByPhoneStartsWith(String keyword) {	
 
				return StreamSupport
				  .stream(CustomerRepository.findByPhoneStartsWith(keyword).spliterator(), false)
				  .collect(Collectors.toList());
			   }
		 	  
			  @Override
			  public Page<customer> ListAll (int pageNum) {			
			    int pageSize = 21;			     
			    Pageable pageable = PageRequest.of(pageNum -1 , pageSize);			     
			    Page<customer> x =  CustomerRepository.findAll(pageable);
                return x ;
    			}

}
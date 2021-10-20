package com.exercise.customer.service;

 

import java.util.List;

import org.springframework.data.domain.Page;

import com.exercise.customer.entity.customer;

 

public interface CustomerService {


             public List<customer> findAll();
             
             public Page<customer> ListAll(int pageNum);

             public List<customer> filter(String keyword);

             public customer findById(int theId);

             public void save(customer theCustomer);

             public void deleteById(int theId);

             public List<customer> findByPhoneStartsWith(String keyword);

}
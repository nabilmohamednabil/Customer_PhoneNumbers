package com.exercise.customer.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.exercise.customer.entity.customer;



@Repository
public interface CustomerRepository extends PagingAndSortingRepository<customer, Integer> {
  
	
    @Query("SELECT x FROM customer x WHERE x.phone LIKE %?1% ")
    public Iterable<customer> filter(String keyword);
    public Iterable<customer> findByPhoneStartsWith(String keyword);
    Page<customer> findAll(Pageable pageable);
 
}
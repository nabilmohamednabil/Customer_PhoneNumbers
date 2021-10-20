package com.exercise.customer.Creation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.exercise.customer.PresentationLayer.DataPresentation;
import com.exercise.customer.Validation.CustomerValidation;
import com.exercise.customer.entity.customer;

@Component
@Scope("prototype")
public class create {
	
	 private List<DataPresentation> FinalEntity ;
	
	 private List<customer> OriginalEntity ;
	 
	 @Autowired
	 private CustomerValidation Validation ;
     
     public create (  CustomerValidation Validation ) {	 
    	 this.Validation     =  Validation ;
    	 this.FinalEntity         =  new ArrayList<>();
    	 this.OriginalEntity =  new ArrayList<>();
     }
     
     public List<DataPresentation> CreateStructure ( List<customer> Y , String state) {
    	 for(customer val: Y){
    		 OriginalEntity.add(val);
    		 }
         for (customer x  : OriginalEntity ) {   
        	 if ( Validation.GetState(x.getPhone()) == state || state.equals("all") ) {
         	 DataPresentation y = new DataPresentation () ;
             y.setId(x.getId());
             y.setName(x.getName());
             y.setPhone(x.getPhone());
             y.setNumber(Validation.GetNumber(x.getPhone()));
             y.setState(Validation.GetState(x.getPhone()));
             y.setCountry(Validation.GetCountryName(x.getPhone()));
             y.setCountryCode("+" + (Validation.GetCountryCode(x.getPhone()).replace('(',' ').replace(')',' ')).trim());
             FinalEntity.add(y);   
        	 }
         }
         return FinalEntity ;  	 
     }
     
     public String GetCountryCodeFromCountryName (String keyword  ) {
    	 String key ; 
    	 key = Validation.GetCountryCodeFromCountryName(keyword);
    	 return key ; 
     }
}

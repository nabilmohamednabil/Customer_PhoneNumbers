package com.exercise.customer.Validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

	private Map<String, String> CountryMapping = new HashMap<>();
	
	public CustomerValidation () {
		CountryMapping.put("(237)", "Cameroon");
		CountryMapping.put("(251)", "Ethiopia");
		CountryMapping.put("(212)", "Morocco");
		CountryMapping.put("(258)", "Mozambique");
		CountryMapping.put("(256)", "Uganda");
	};
	public String GetCountryName(String x) {
		String name = this.GetCountryCode(x);
		return CountryMapping.get(name);
	};
	public String GetCountryCode(String x) {
		  String result = null ;
		  String sub = x.substring(0, 5);
		 
	      for (String key : CountryMapping.keySet()) {
	    	
	          if( sub.equals(key) ) 
	            { 
	            	result = key ;
	            }
	      }
	      return result ;
	};
	public List<String> GetListCountryCodes (){
		
		List<String> myList = new ArrayList<>();
	    myList = CountryMapping.keySet().stream().collect(Collectors.toList());
		List<String> CountrySortedList = myList.stream().sorted().collect(Collectors.toList());
		return CountrySortedList ;
	}
	public List<String> GetListCountryNames (){
		
		List<String> myList = new ArrayList<>();
		myList = CountryMapping.values().stream().collect(Collectors.toList());
		List<String> CountrySortedList = myList.stream().sorted().collect(Collectors.toList());
		return  CountrySortedList ;
	}
	public String GetState (String x) {
		String Valid = "not valid" ;
		String country = GetCountryName(x);
		Valid = this.Validate(x, country);
		return Valid;
	}
	public String GetCountryCodeFromCountryName (String x) {
		  String result = null ;		  
	      for (String key : CountryMapping.keySet()) {

	          if( CountryMapping.get(key).contains(x) ) 
	            { 
	            	result = key ;
	            }
	      }
	      return result ;
	};
	public String Validate (String x , String Country) {
		String result = "not valid" ;
		Boolean Valid = true ;
		String sub ;
		if(x.substring(5, 6).equals(" ") ) {
                sub = x.substring(6);
		}
		else {
			   sub = x.substring(5);
		}
		Valid = this.CheckDigits(sub);
		if(!Valid ) {return result ; };		
		switch (Country) {
		case "Mozambique" :		
			if( (sub.substring(0, 1).equals( "2" )  || sub.substring(0, 1).equals( "8" )  ) && ( ((sub.length()-1) == 7) || ((sub.length())-1 == 8) ) ) {
				result = "valid" ;
			}
			break ;
		case "Cameroon" :
			if( (sub.substring(0, 1).equals( "2" )  || sub.substring(0, 1).equals( "8" )  || sub.substring(0, 1).equals( "3" ) || sub.substring(0, 1).equals( "6" )) && ( ((sub.length()-1) == 7) || ((sub.length())-1 == 8) ) ) {
				result = "valid" ;
			}
			break ;
		case "Ethiopia" :
			if (   ( ((sub.length()) == 10) &&   (  Integer.parseInt(sub.substring(0, 1))  >= 1 )  
			         &&   (Integer.parseInt((sub.substring(0, 1)) ) <= 5)  &&    ((Integer.parseInt((sub.substring(1, 2)) ) <= 9) )
			         && ((Integer.parseInt((sub.substring(1, 2)) ) >= 0) ))  
			   ||(   ((sub.length()) == 9) &&   (  Integer.parseInt(sub.substring(0, 1))  >= 1 )  
			         &&   (Integer.parseInt((sub.substring(0, 1)) ) <= 9)  )) 
			{
			  result = "valid" ;
			}
			break ;
		case "Morocco" :
			if(  (  Integer.parseInt(sub.substring(0, 1))  >= 5 )  
					
					&& ((Integer.parseInt((sub.substring(0, 1)) ) <= 9) )    
					
					&& (  ((sub.length())-1 == 8) )   ) {
				result = "valid" ;
			}
			break ;
		case "Uganda" :
			if( ( ( (sub.length())) == 9)   ) {
				result = "valid" ;
			}
			break ;
		default :
			break ; 
		}
		return result ;
	}
	
	private Boolean CheckDigits (String x) {		
		Boolean result = true  ;		
		for(int i=0 ; i< x.length() ; i++) {
			if ( ! Character.isDigit(x.charAt(i))  ) {
				return false ;
			}
		}
		return true ;
	}
	
	public String GetNumber (String x ) {
	String sub ;
    if(x.substring(5, 6).equals(" ") ) {
            sub = x.substring(6);
	}
	else {
		   sub = x.substring(5);
	} 
    return Integer.toString(sub.length() )  ;
	}
	
}

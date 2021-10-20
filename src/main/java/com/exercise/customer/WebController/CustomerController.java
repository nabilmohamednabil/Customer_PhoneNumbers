package com.exercise.customer.WebController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exercise.customer.entity.customer;
import com.exercise.customer.service.CustomerService;
import com.exercise.customer.PresentationLayer.DataPresentation;
import com.exercise.customer.Validation.CustomerValidation; 
import com.exercise.customer.Creation.create ;

@Controller
@RequestMapping("/customers")
@Scope("prototype")
public class CustomerController {

       private CustomerService customerservice;     
     
       @Autowired
       private create create ;
       
       public CustomerController(CustomerService customerservice  , create create ) {
             this.customerservice = customerservice;
             this.create = create ;
       }

       @GetMapping("/list")
       public String listCustomers(Model theModel) {
              
           List<customer> FirstEntityList       = customerservice.findAll();
           List<DataPresentation> customers     = create.CreateStructure(FirstEntityList ,"all");
        
            theModel.addAttribute("Pagination", "false");
            theModel.addAttribute("FinalEnity", customers);
            return "Presentation";
       }
                    
       @GetMapping("/list/page/{pageNum}")
       public String listCustomersPaging(Model theModel , @PathVariable(name = "pageNum") int pageNum) {

    	    Page<customer> page = customerservice.ListAll(pageNum);  
    	    List<customer> FirstEntityList = page.getContent(); 
    	    if (pageNum > page.getTotalPages()) return "Presentation";
    	    List<DataPresentation> customers  = create.CreateStructure(FirstEntityList ,"all");           
         
            theModel.addAttribute("currentPage", pageNum);
            theModel.addAttribute("totalPages", page.getTotalPages());
            theModel.addAttribute("Pagination", "true");
            theModel.addAttribute("FinalEnity", customers);        
            return "Presentation";
       }
            
       @GetMapping("/filter")
       public String filterCustomers(Model theModel  , @Param("keyword") String keyword , @Param("flag") String flag ,
    		   @Param("flag2") String flag2) {
    	   
           theModel.addAttribute("keyword", keyword);
           theModel.addAttribute("flag2", flag2); 
           theModel.addAttribute("flag", flag);
           
           String key = create.GetCountryCodeFromCountryName(keyword);
           if (key == null) return "Presentation";
           List<customer> FirstEntityList = customerservice.findByPhoneStartsWith(key);
           List<DataPresentation> customers = new ArrayList<>(); 
           
  	     if(  (flag == null && flag2 == null ) || (flag != null && flag2 != null ))
  	        {  	   
              customers  = create.CreateStructure(FirstEntityList ,"all");
	        }
  	     else if (flag != null ) 
    	    {  	    	          
             customers  = create.CreateStructure(FirstEntityList ,"valid");         
    	    }
  	     else if (flag2.equals("on")) 
  	        {     
             customers  = create.CreateStructure(FirstEntityList ,"not valid");
  	        }
  	     else 
  	        {
  	    	customers  = create.CreateStructure(FirstEntityList ,"empty");
  	    	return "Presentation"; 
  	        }
  	    theModel.addAttribute("Pagination", "false");
        theModel.addAttribute("FinalEnity", customers);     
        return "Presentation";
       }
              
}
package com.shoaib.springboot.controller;



import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shoaib.springboot.model.Employee;
import com.shoaib.springboot.service.EmployeeService;

@Controller
class GetPartyEmployee {
	
	@Autowired
	private EmployeeService empService;
	
	private Employee emps = new Employee();

	//@GetMapping("http://dummy.restapiexample.com/api/v1/employee/{employeeId}")

//	@RequestMapping(value = {"/karo/{employeeId}"},method = {RequestMethod.GET})
//	public ResponseEntity<Employee> retrieveDetailsForEmployee(@PathVariable String employeeId) {
//		System.out.println("********************* here ****************** ");
//		emps  =empService.retrieveQuestion(employeeId);
//		System.out.println("username ********** " +emps.getId());
//		//System.out.println("username ********** " +emps.getHeaders());
//	    //modelObj.put("todos", emps);
//	    //return "get3PartyEmployeelist";
//	    // return emps;
//			if(emps == null){
//				return new ResponseEntity<Employee>(emps,HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Employee>(emps,HttpStatus.CONFLICT);
//			}
//	}
	@RequestMapping(value = {"/get3PartyEmployeelist/{employeeId}"},method = {RequestMethod.GET})
	public String retrieveDetailsForEmployee(@PathVariable String employeeId,ModelMap modelObj) {
		System.out.println("********************* here ****************** ");
		emps  =empService.retrieveQuestion(employeeId);
		System.out.println("username ********** " +emps.getId());
		//System.out.println("username ********** " +emps.getHeaders());
	    modelObj.put("emps", emps);
	    return "get3PartyEmployeelist2";
	}


}
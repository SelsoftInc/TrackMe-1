package com.selsoft.trackme.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.service.LeaseService;

@RestController
@RequestMapping(value = "/lease")
public class LeaseController {
	
	private static final Logger logger = Logger.getLogger(LeaseController.class);
	
	


	@Autowired
	private LeaseService leaseService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Object updatePort(@RequestBody Lease lease){
	
	
		
			Errors	error= leaseService.priorDataValidation(lease);
			error.getError();
				
				if(error!=null){
					return  new ResponseEntity<Object>(error.getError(), HttpStatus.BAD_REQUEST);
				}
				
				return null;
	
	}

}


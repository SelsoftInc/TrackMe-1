package com.selsoft.trackme.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.LeaseService;

@RestController
@RequestMapping(value = "/lease")
public class LeaseController {

	private static final Logger logger = Logger.getLogger(LeaseController.class);

	// private static PropertyStatus propertyStatus=new PropertyStatus();

	@Autowired
	private LeaseService leaseService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ValidError> createLease(@RequestBody Lease lease) {

		ValidError error = leaseService.priorDataValidation(lease);

		return null;

	}

}

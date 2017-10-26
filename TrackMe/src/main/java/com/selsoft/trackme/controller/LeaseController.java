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

	@RequestMapping(value = "/createLease", method = RequestMethod.POST)
	public ValidError createLease(@RequestBody Lease lease) {

		ValidError error = leaseService.validateNewLeaseData(lease);

		logger.info(lease.getPropertyId() + " data comes into LeaseControllercreateLease() for processing");
		if (error == null) {
			leaseService.createLease(lease);
			error=new ValidError("SUCESS", "Property is ACTIVE");
		}
		return error;

	}

}

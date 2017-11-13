package com.selsoft.owner.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.owner.constants.TrackMeConstants;
import com.selsoft.owner.model.Owner;
import com.selsoft.owner.model.OwnerStatus;
import com.selsoft.owner.service.OwnerService;

@RestController
@RequestMapping(value = "/owner")

/**
 * 
 * @author sudhansu
 *
 */

public class OwnerController {

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(OwnerController.class);

	@Autowired
	OwnerService ownerService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// owners--------------------------------------------------------
	@RequestMapping(value = "/getAllPropertyOwners", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Owner> getAllPropertyOwners() {
		return ownerService.getAllPropertyOwners();
	}

	// ------------------- save a Owners
	// --------------------------------------------------------
	@RequestMapping(value = "/saveNewOwner", method = RequestMethod.POST)
	public void saveNewOwner(@RequestBody Owner owner) {
		ownerService.saveNewOwner(owner);
	}

	// ------------------- gets status of an Owner
	// --------------------------------------------------------
	@RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
	public void checkStatus(@RequestParam("status") OwnerStatus status) {
	}

}

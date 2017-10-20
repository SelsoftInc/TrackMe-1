package com.selsoft.trackme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.OwnerStatus;

import com.selsoft.trackme.service.OwnerService;

@RestController
@RequestMapping(value = "/owner")
public class OwnerController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OwnerController.class);

	@Autowired
	OwnerService ownerService;

	@RequestMapping(value = "get-owners", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Owner> getAllPropertyOwners() {
		return ownerService.getAllPropertyOwners();
	}

	@RequestMapping(value = "save-user", method = RequestMethod.POST)
	public void saveNewOwner(@RequestBody Owner owner) {
		ownerService.saveNewOwner(owner);
	}

	@RequestMapping(value = "status", method = RequestMethod.GET)
	public void checkStatus(@RequestParam("status") OwnerStatus status) {
	}

}

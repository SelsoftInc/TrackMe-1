package com.selsoft.trackme.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.service.PropertyService;

@RestController
@RequestMapping(value = "/property")
public class PropertyController {

	private static final Logger logger = Logger.getLogger(PropertyController.class);

	@Autowired
	PropertyService propertyService;

	@RequestMapping(value = "add-property", method = RequestMethod.POST)
	public void addNewProperty(@RequestBody Property property) {
		logger.info(
				property.getOwnerFirstName() + " data comes into PropertyController addNewProperty() for processing");

		propertyService.saveNewProperty(property);

	}

	@RequestMapping(value = "setPropertyAsActive", method = RequestMethod.PUT)
	public ResponseEntity<Errors> setPropertyAsActive(@RequestBody Property property) {
		logger.info(
				property.getPropertyStatus() + " data comes into PropertyController setPropertyAsActive() for processing");

		Errors errors = propertyService.setPropertyAsActive(property);

		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);

	}

}

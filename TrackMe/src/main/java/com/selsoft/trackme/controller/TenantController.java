package com.selsoft.trackme.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selsoft.trackme.email.service.MailSenderService;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.service.TenantService;
import com.selsoft.trackme.service.UserService;

public class TenantController {
private static final Logger logger = Logger.getLogger(TenantController.class);

	@Autowired
	private TenantService tenantService;
	@Autowired(required = true)
	private MailSenderService mailSender;

	@RequestMapping(value = "add-property", method = RequestMethod.PUT)
	public void addNewTenant(@RequestBody Tenant tenant) {
		logger.info(
				tenant.getTenantFirstName() + " data comes into PropertyController addNewProperty() for processing");

		tenantService.addNewTenant(tenant);

	}

	@RequestMapping(value = "/save-new-tenant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tenant> saveNewTenant() {
		return tenantService.saveNewTenant();
	}

	@RequestMapping(value = "/get-all-tenants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tenant> getAllTenants() {
		return tenantService.getAllTenants(null);
	}
	
	
}
package com.selsoft.trackme.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.service.TenantService;

@RestController
public class TenantController {
	private static final Logger logger = Logger.getLogger(TenantController.class);

	@Autowired
	private TenantService tenantService;

	//@Autowired(required = true)

	@RequestMapping(value = "/add-tenant", method = RequestMethod.PUT)
	public void addNewTenant(@RequestBody Tenant tenant) {
		logger.info(tenant.getTenantFirstName() + " data comes into TenantController addNewTenant() for processing");
		tenantService.addNewTenant(tenant);
	}

	@RequestMapping(value = "/get-all-tenants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tenant> getAllTenants() {
		return tenantService.getAllTenants(null);
	}

}
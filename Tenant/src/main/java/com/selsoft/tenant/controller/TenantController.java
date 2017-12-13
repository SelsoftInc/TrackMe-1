package com.selsoft.tenant.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.tenant.constants.TenantConstants.TENANT_STATUS;
import com.selsoft.tenant.model.Errors;
import com.selsoft.tenant.model.Tenant;
import com.selsoft.tenant.service.TenantService;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {
	private static final Logger logger = Logger.getLogger(TenantController.class);

	@Autowired
	private TenantService tenantService;

	/*
	 * //-------------------Add new tenants--------------------------------------------------------
	 */

	//@RequestMapping(value = "/addNewTenant", method = RequestMethod.PUT)
	@RequestMapping(value = "/addNewTenant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Errors> addNewTenant(@RequestBody Tenant tenant) {
		
		Errors errors=null;	
		try {
		    errors= tenantService.saveNewTenant(tenant);
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);
		
		
		/*
		logger.info(tenant.getTenantFirstName() + " data comes into TenantController addNewTenant() for processing");
		tenantService.addNewTenant(tenant);*/
	}

	/*
	 * //-------------------Retrieve All tenants--------------------------------------------------------
	 */

	@RequestMapping(value = "/getAllTenants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tenant> getAllTenants(String status) {
		return tenantService.getAllTenants(TENANT_STATUS.NEW.toString());
	}

	/*
	 * //-------------------Retrieve New tenants--------------------------------------------------------
	 */

	@RequestMapping(value = "/getNewTenants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tenant> getNewTenants() {
		return tenantService.getAllTenants("NEW");
	}
}
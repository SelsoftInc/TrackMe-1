package com.selsoft.tenant.service;

import java.util.List;

import com.selsoft.tenant.model.Errors;
import com.selsoft.tenant.model.Tenant;



public interface TenantService {

	public Object addNewTenant(Tenant tenant) throws Throwable;
	public List<Tenant> getAllTenants(String status);
	String getTenantStatusById(int id);
	public Errors saveNewTenant(Tenant tenant);
	
}

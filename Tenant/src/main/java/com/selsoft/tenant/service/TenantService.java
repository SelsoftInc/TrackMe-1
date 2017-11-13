package com.selsoft.tenant.service;

import java.util.List;

import com.selsoft.tenant.model.Tenant;

public interface TenantService {

	public void addNewTenant(Tenant tenant);

	public List<Tenant> getAllTenants(String status);

	String getTenantStatusById(int id);

	public List<Tenant> saveNewTenant();

}

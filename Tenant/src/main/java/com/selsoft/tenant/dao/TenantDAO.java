package com.selsoft.tenant.dao;

import java.util.List;

import com.selsoft.tenant.model.Tenant;
public interface TenantDAO {

	public void saveNewTenant(Tenant tenant);
	List<Tenant> findAll();
	List<Tenant> fetchTenants(String status);

}

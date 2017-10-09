package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Tenant;

public interface TenantService {

	public void addNewTenant(Tenant tenant);

	public List<Tenant> tenantList();

}

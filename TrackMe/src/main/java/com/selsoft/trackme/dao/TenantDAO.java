package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.User;

public interface TenantDAO {

	public void saveNewTenant(Tenant tenant);

	List<Tenant> findAll();

}

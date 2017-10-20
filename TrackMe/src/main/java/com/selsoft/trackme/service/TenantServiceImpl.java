package com.selsoft.trackme.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.selsoft.trackme.dao.TenantDAO;
import com.selsoft.trackme.model.Tenant;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantDAO;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TenantService.class);

	@Override
	public void addNewTenant(Tenant tenant) {
		tenantDAO.saveNewTenant(tenant);

	}

	@Override
	public List<Tenant> getAllTenants(String status) {
		return tenantDAO.fetchTenants(status);
	}

	@Override
	public List<Tenant> saveNewTenant() {
		return tenantDAO.findAll();
	}

}

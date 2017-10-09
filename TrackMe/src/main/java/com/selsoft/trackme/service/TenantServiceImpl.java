package com.selsoft.trackme.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.TenantDAO;
import com.selsoft.trackme.dao.UserDao;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.utils.Utils;
import com.selsoft.trackme.validation.UserValidation;

@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantDAO;

	private static final Logger logger = Logger.getLogger(TenantService.class);

	@Override
	public void addNewTenant(Tenant tenant) {
		tenantDAO.saveNewTenant(tenant);

	}

	@Override
	public List<Tenant> tenantList() {
		return tenantDAO.findAll();
	}

}

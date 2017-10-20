package com.selsoft.trackme.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Tenant;

@Repository
@Qualifier("tanantDAO")
public class TenantDAOImpl implements TenantDAO {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private MongoTemplate template;

	final String COLLECTION = "TENANT";

	public void saveNewTenant(Tenant tenant) {
		template.save(tenant);
	}

	public List<Tenant> findAll() {
		return (List<Tenant>) template.findAll(Tenant.class);
	}

	@Override
	public List<Tenant> fetchTenants(String status) {
		List<Tenant> tenantList = null;
		if (status != null) {
			tenantList = template.findAll(Tenant.class);
		} else {
			Query query = new Query(Criteria.where("status").is(status));
			tenantList = template.find(query, Tenant.class);
		}
		return tenantList;
	}

}

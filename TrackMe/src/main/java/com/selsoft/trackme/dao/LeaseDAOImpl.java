package com.selsoft.trackme.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.Tenant;

@Repository
public class LeaseDAOImpl implements LeaseDAO {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LeaseDAOImpl.class);

	@Autowired
	private MongoTemplate template;
	
	@Override
	public String getPropertyStatusById(int id) {
		Query query = new Query(Criteria.where("propertyId").is(id));
		Property property = template.findOne(query, Property.class);

		return property.getPropertyStatus();
	}

	public String getTenantStatusById(int id) {

		Query query = new Query(Criteria.where("tenantId").is(id));
		Tenant tenant = template.findOne(query, Tenant.class);

		return tenant.getTenantStatus();
	}

	@Override
	public void createLease(Lease lease) {
		template.save(lease);
	}

}

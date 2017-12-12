package com.selsoft.tenant.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DuplicateKeyException;
import com.selsoft.tenant.constants.TrackMeConstants;
import com.selsoft.tenant.model.Tenant;
import com.selsoft.tenant.utils.TenantException;

@Repository
@Qualifier("tanantDAO")
public class TenantDAOImpl implements TenantDAO {
	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(TenantDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	final String COLLECTION = "TENANT";

	/**
	 * saves new tenant to tenant table
	 */
	public void saveNewTenant(Tenant tenant){
		
			try {
				template.insert(tenant);
			} catch(Exception e) {
				logger.info(tenant.getTenantEmailId() + " already exists in the database");
				//throw new TenantException("Error", "Owner with the email id " + tenant.getTenantEmailId() + " already exists, please add a owner with a different email id.");
			} 
			}
		
	/**
	 * find all tenants
	 */
	public List<Tenant> findAll() {
		return (List<Tenant>) template.findAll(Tenant.class);
	}

	@Override

	/**
	 * fetching tenants based on status
	 */
	public List<Tenant> fetchTenants(String status) {

		List<Tenant> tenantList = null;

		if (status != null) {

			Query query = new Query(Criteria.where("tenantStatus").is(status));
			tenantList = template.find(query, Tenant.class);
		} else {

			tenantList = template.findAll(Tenant.class);
		}

		return tenantList;
	}

}

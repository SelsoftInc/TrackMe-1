package com.selsoft.demo.dao;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;
import com.selsoft.demo.model.Lease;
import com.selsoft.demo.model.Tenant;
@Repository
public class AutoBillingDAOImpl implements AutoBillingDAO {

	private static final Logger logger = Logger.getLogger(AutoBillingDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public List<Lease> getActiveLease() {
		List<Lease> autoBillingList = null;
		String leaseEndDate = "2017-12-05";
         String currentDate="2017-06-05";
		if (StringUtils.isNotBlank(leaseEndDate)) {
			Query query = new Query().addCriteria(Criteria.where("currentDate").gte(leaseEndDate));
			autoBillingList = template.find(query, Lease.class);
		}
		return autoBillingList;

	}

	@Override
	public List<String> updateLeaseDate(List<Lease> lease){
		Tenant tenant = new Tenant();
		String tenantId = tenant.getTenantId();
		 List<String> tenantEmails=null;
		Update update = new Update();
		tenant.setTenantId(tenant.getTenantId());
		Query query = new Query(Criteria.where(" tenantId").is(tenantId));
		tenant.setTenantEmailId(tenant.getTenantEmailId());
		update.set("nextBillDate", "1st of next month");
		template.updateFirst(query, update, Lease.class);
		return tenantEmails;
		
	}
	@Override
	public SimpleMailMessage sendEmailForBilling(String tenantEmails) {
		
			Query query = new Query(Criteria.where("tenantEmails").is(tenantEmails.toLowerCase()));
			List<Tenant> tenantExist = template.find(query, Tenant.class);
			return (SimpleMailMessage) tenantExist;
		}

	}


package com.selsoft.auto.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.selsoft.auto.model.Lease;
import com.selsoft.auto.model.Tenant;
import com.selsoft.auto.utils.AutoBillingException;
@Repository
public class AutoBillingDAOImpl implements AutoBillingDAO {

	private static final Logger logger = Logger.getLogger(AutoBillingDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public List<String> autoBilling() {
		List<Lease> activeLeaseList = null;
		List<String> tenantIds = new ArrayList<>();
		List<String> leaseIds = new ArrayList<>();
		int nextMonth = 0;
		int billYear = 0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String currentDate = df.format(calendar.getTime());

		int currentMonth = calendar.getTime().getMonth();
		int currentYear = calendar.getTime().getYear();

		if (currentMonth == 11) {
			nextMonth = 0;
			billYear = currentYear + 1;
		} else {
			nextMonth = currentMonth + 1;
			billYear = currentYear;
		}

		String nextBillDate = billYear + "-" + nextMonth + "-01";
     	Query query = new Query(Criteria.where("leaseEndDate").gte(currentDate));
		activeLeaseList = template.find(query, Lease.class);

		for (Lease lease : activeLeaseList) {
			leaseIds.add(lease.getLeaseId());
		}

		for (Lease lease : activeLeaseList) {
			tenantIds.add(lease.getTenantId());
		}

		updateLeaseDate(leaseIds, nextBillDate);
		List<String> mails = getTenantEmails(tenantIds);
      
		return mails;

	}

	private void updateLeaseDate(List<String> leaseIds, String nextBillDate)  {
		
		Query query = new Query(Criteria.where("leaseId").in(leaseIds));
		Update update = new Update();
		update.set("leaseEndDate", nextBillDate);
		template.updateFirst(query, update, Lease.class);
				
	}

	private List<String> getTenantEmails(List<String> tenantIds)  {
		List<String> tenantEmails = new ArrayList<>();
    
		Query query = new Query(Criteria.where("tenantId").in(tenantIds));
		List<Tenant> tenantList = template.find(query, Tenant.class);

		for (Tenant tenant : tenantList) {
			tenantEmails.add(tenant.getTenantEmailId());
		}
     
     	return tenantEmails;
	}

}
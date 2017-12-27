package com.selsoft.demo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.demo.model.Lease;




@Repository
public class AutoBillingDAOImpl implements  AutoBillingDAO{

	private static final Logger logger = Logger.getLogger(AutoBillingDAOImpl.class);

	@Autowired
	private MongoTemplate template;
	
	public List<Lease> getAllRecords(String endDate) {
		return null;
		/*List<Lease> autoBillingList = null;
		
		Query query = new Query(Criteria.where("endDate").is(endDate.getEndDate()));
		Update update = Update.update("nextBillDate", endDate.getNextBillDate());
		template.updateFirst(query, update, Lease.class);

		return autoBillingList;
	}*/
	
}
}

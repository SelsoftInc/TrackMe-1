package com.selsoft.trackme.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.PropertyStatus;
@Repository
public class LeaseDAOImpl implements LeaseDAO {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OwnerDaoImpl.class);

	@Autowired
	private MongoTemplate template;


	@Override
	public Object priorDataValidation(Lease lease) {
		Query query = new Query(Criteria.where("PropertyId").is(lease.getPropertyId()));
		Update update = new Update();
		update.set("propertyStatus", "ACTIVE");
		template.updateFirst(query, update, Property.class);
		
		Object propertyStatus;
		/*if(propertyStatus.equals("NEW")){
			
			//logger.info(user.getFirstName() + " data comes into UserController saveUser() for processing");

		}*/
		return null;
		
	}

}

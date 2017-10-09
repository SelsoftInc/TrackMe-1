package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.User;

@Repository
@Qualifier("propertyDAO")
public class PropertyDAOImpl implements PropertyDAO {
	private static final Logger logger = Logger.getLogger(PropertyDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	final String COLLECTION = "PROPERTY";

	public void saveNewProperty(Property property) {
		template.save(property);
	}

	@Override
	public Owner checkOwner(int ownerId) {
		Query query = new Query(Criteria.where("ownerId").is(ownerId));
		List<Owner> ownerExist = template.find(query, Owner.class);
		return ownerExist.get(0);
	}

}

package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;

@Repository
@Qualifier("propertyDAO")
public class PropertyDAOImpl implements PropertyDAO {
	@SuppressWarnings("unused")
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

	@Override
	public Errors setPropertyAsActive(Property property) {
		Query query = new Query(Criteria.where("PropertyId").is(property.getPropertyId()));
		Update update = new Update();
		update.set("propertyStatus", "ACTIVE");
		template.updateFirst(query, update, Property.class);
		return null;

	}



}

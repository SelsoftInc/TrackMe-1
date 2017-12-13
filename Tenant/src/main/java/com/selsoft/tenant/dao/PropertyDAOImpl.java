package com.selsoft.tenant.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.tenant.constants.TrackMeConstants;
import com.selsoft.tenant.model.Errors;
import com.selsoft.tenant.model.Owner;
import com.selsoft.tenant.model.Property;
import com.selsoft.tenant.model.RentalDetail;

@Repository
@Qualifier("propertyDAO")
public class PropertyDAOImpl implements PropertyDAO{

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(PropertyDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	final String COLLECTION = "PROPERTY";

	/**
	 * save new property to property table
	 */
	@Override
	public void saveNewProperty(Property property) {
		template.save(property);
		template.save(property.getRentalDetail());
	}

	@Override

	/**
	 * It checks owner based on ownerid
	 */
	public Owner checkOwner(int ownerId) {
		Query query = new Query(Criteria.where("ownerId").is(ownerId));
		List<Owner> ownerExist = template.find(query, Owner.class);
		return ownerExist.get(0);
	}

	@Override
	/**
	 * It sets property as active
	 */
	public Errors setPropertyAsActive(Property property) {
		Query query = new Query(Criteria.where("PropertyId").is(property.getPropertyId()));
		Update update = new Update();
		update.set("propertyStatus", "ACTIVE");
		template.updateFirst(query, update, Property.class);
		return null;

	}

	@Override
	public List<Property> getAllProperties(String status) {

		List<Property> propertyList = null;
		Query query = new Query(Criteria.where("propertyStatus").is(status));
		propertyList = template.find(query, Property.class);

		return propertyList;
	}

	@Override
	public List<Property> findAll() {

		return (List<Property>) template.findAll(Property.class);
	}

	@Override
	public void updateProperty(RentalDetail rentalDetail, int propertyId) {
		Query query = new Query(Criteria.where("propertyId").is(propertyId));
		Update update = new Update();
		rentalDetail.setProperytId(propertyId);
		update.set("rentalDetail", rentalDetail);
		template.updateFirst(query, update, Property.class);
	}
	
}

package com.selsoft.owner.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.owner.constants.TrackMeConstants;
import com.selsoft.owner.model.Owner;

@Repository
public class OwnerDaoImpl implements OwnerDao {

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(OwnerDaoImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override

	/**
	 * saves new owner in owner table
	 */
	public void saveNewOwner(Owner owner) {

		template.save(owner);
	}

	@Override
	/* GET */
	public List<Owner> getAllPropertyOwners() {
		List<Owner> ownerList = template.findAll(Owner.class);
		return ownerList;
	}

	public List<Owner> checkStatus(String status) {

		List<Owner> ownerList = null;
		Query query = new Query(Criteria.where("ownerStatus").is(status));
		ownerList = template.find(query, Owner.class);
		return ownerList;

	}

}

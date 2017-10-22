package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Owner;

@Repository
public class OwnerDaoImpl implements OwnerDao {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OwnerDaoImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public void saveNewOwner(Owner owner) {

		template.save(owner);
	}

	@Override
	public List<Owner> getAllPropertyOwners() {
		List<Owner> ownerList = template.findAll(Owner.class);
		return ownerList;
	}

}

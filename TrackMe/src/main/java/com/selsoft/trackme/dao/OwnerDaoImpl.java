package com.selsoft.trackme.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.User;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Repository
public class OwnerDaoImpl implements OwnerDao {

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

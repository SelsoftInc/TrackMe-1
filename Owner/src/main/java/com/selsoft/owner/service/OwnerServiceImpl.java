package com.selsoft.owner.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.owner.constants.TrackMeConstants;
import com.selsoft.owner.dao.OwnerDao;
import com.selsoft.owner.model.Owner;


@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(OwnerService.class);

	/**
	 * save the new owner
	 */

	public void saveNewOwner(Owner owner) {

		// handler method to save/update owner
		ownerDao.saveNewOwner(owner);
	}

	/**
	 * gets all the property owners from owner table
	 */
	@Override
	public List<Owner> getAllPropertyOwners() {
		return ownerDao.getAllPropertyOwners();

	}


	@Override
	public List<Owner> checkStatus(String status) {
		
		 return ownerDao.checkStatus(status);
		
	}

}

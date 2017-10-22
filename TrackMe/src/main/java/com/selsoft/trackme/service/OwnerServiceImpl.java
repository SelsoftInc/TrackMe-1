package com.selsoft.trackme.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.OwnerDao;
import com.selsoft.trackme.model.Owner;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OwnerService.class);

	public void saveNewOwner(Owner owner) {
		ownerDao.saveNewOwner(owner);
	}

	@Override
	public List<Owner> getAllPropertyOwners() {
		return ownerDao.getAllPropertyOwners();

	}

}

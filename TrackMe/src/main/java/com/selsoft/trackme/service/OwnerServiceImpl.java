package com.selsoft.trackme.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.OwnerDao;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.User;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	private static final Logger logger = Logger.getLogger(OwnerService.class);

	public void saveNewOwner(Owner owner) {
		ownerDao.saveNewOwner(owner);
	}

	@Override
	public List<Owner> getAllPropertyOwners() {
		return ownerDao.getAllPropertyOwners();

	}

}

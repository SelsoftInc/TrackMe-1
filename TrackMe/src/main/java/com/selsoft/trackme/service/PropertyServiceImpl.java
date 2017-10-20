package com.selsoft.trackme.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.selsoft.trackme.dao.PropertyDAO;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.PropertyStatus;

@Service("propertyService")

public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDAO propertyDAO;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PropertyServiceImpl.class);

	public void saveNewProperty(Property property) {

		Owner owner = propertyDAO.checkOwner(property.getOwnerid());

		if (owner != null) {
			property.setOwnerFirstName(owner.getOwnerFirstName());
			property.setOwnerLastName(owner.getOwnerLastName());
			propertyDAO.saveNewProperty(property);

		}
	}

	@Override
	public Errors setPropertyAsActive(Property property) {
		return null;

		// propertyDAO.setPropertyAsActive(property.getPropertyStatus());

	}

	@Override
	public Property findPropertyStatus(String status) {

		PropertyStatus proprtyStatus = propertyDAO.findPropertyStatus(status);
		return null;
	}

}

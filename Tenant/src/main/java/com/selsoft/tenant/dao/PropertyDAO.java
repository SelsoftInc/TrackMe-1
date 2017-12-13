package com.selsoft.tenant.dao;

import java.util.List;

import com.selsoft.tenant.model.Errors;
import com.selsoft.tenant.model.Owner;
import com.selsoft.tenant.model.Property;
import com.selsoft.tenant.model.RentalDetail;

public interface PropertyDAO {
	
		public void saveNewProperty(Property property);
		public Owner checkOwner(int ownerId);
		public Errors setPropertyAsActive(Property property);
		public List<Property> getAllProperties(String status);
		public void updateProperty(RentalDetail rentalDetail, int propertyId);
		List<Property> findAll();
	}




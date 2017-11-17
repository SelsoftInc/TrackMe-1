package com.selsoft.lease.dao;

import java.util.List;

import com.selsoft.lease.model.Errors;
import com.selsoft.lease.model.Owner;
import com.selsoft.lease.model.Property;
import com.selsoft.lease.model.RentalDetail;



public interface PropertyDAO {

	public void saveNewProperty(Property property);

	public Owner checkOwner(int ownerId);

	public Errors setPropertyAsActive(Property property);

	public List<Property> getAllProperties(String status);

	public void updateProperty(RentalDetail rentalDetail, int propertyId);

	List<Property> findAll();

}
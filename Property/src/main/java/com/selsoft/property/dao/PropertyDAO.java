package com.selsoft.property.dao;

import java.util.List;

import com.selsoft.property.model.Errors;
import com.selsoft.property.model.Owner;
import com.selsoft.property.model.Property;
import com.selsoft.property.model.RentalDetail;

public interface PropertyDAO {

	public void saveNewProperty(Property property);

	public Owner checkOwner(int ownerId);

	public Errors setPropertyAsActive(Property property);

	public List<Property> getAllProperties(String status);

	public void updateProperty(RentalDetail rentalDetail, int propertyId);

	List<Property> findAll();

}

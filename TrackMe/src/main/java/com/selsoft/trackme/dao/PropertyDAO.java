package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.Tenant;

public interface PropertyDAO {

	public void saveNewProperty(Property property);

	public Owner checkOwner(int ownerId);
	
	public Errors setPropertyAsActive(Property property);
	
	public List<Property>  getAllProperties(String status);

	List<Property> findAll();

}

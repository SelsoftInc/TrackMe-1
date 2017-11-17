package com.selsoft.property.service;

import java.util.List;

import com.selsoft.property.model.Errors;
import com.selsoft.property.model.Property;

public interface PropertyService {

	public void saveNewProperty(Property property);

	public Errors setPropertyAsActive(Property property);

	public List<Property> getAllProperties(String status);

	String getPropertyStatusById(int id);

}

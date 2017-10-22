package com.selsoft.trackme.service;


import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Property;

public interface PropertyService {

	public void saveNewProperty(Property property);
	public Errors setPropertyAsActive(Property property);
	

}

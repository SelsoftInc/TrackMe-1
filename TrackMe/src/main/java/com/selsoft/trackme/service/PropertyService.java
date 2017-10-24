package com.selsoft.trackme.service;


import java.util.List;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.Tenant;

public interface PropertyService {

	public void saveNewProperty(Property property);
	public Errors setPropertyAsActive(Property property);
	public List<Property>  getAllProperties(String status);

}
 
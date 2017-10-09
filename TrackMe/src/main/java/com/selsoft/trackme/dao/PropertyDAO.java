package com.selsoft.trackme.dao;

import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Property;

public interface PropertyDAO {

	public void saveNewProperty(Property property);

	public Owner checkOwner(int ownerId);

}

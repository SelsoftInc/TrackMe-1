package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.User;

public interface OwnerDao {

	public void saveNewOwner(Owner owner);

	public List<Owner> getAllPropertyOwners();

}

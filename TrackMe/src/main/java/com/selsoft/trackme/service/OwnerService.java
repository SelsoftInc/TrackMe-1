package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.User;

public interface OwnerService {

	public void saveNewOwner(Owner owner);

	public List<Owner> getAllPropertyOwners();

}

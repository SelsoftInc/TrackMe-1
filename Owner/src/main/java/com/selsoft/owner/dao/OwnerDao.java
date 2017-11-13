package com.selsoft.owner.dao;

import java.util.List;

import com.selsoft.owner.model.Owner;

public interface OwnerDao {

	public void saveNewOwner(Owner owner);

	public List<Owner> getAllPropertyOwners();

	public void checkStatus(Owner status);

}

package com.selsoft.owner.service;

import java.util.List;

import com.selsoft.owner.model.Owner;

public interface OwnerService {

	public void saveNewOwner(Owner owner);

	public List<Owner> getAllPropertyOwners();

	public List<Owner> checkStatus(String status);

}

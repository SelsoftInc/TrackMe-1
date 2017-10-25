package com.selsoft.trackme.dao;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Lease;

public interface LeaseDAO {
	
	Errors priorDataValidation(Lease lease);
	String getPropertyStatusById(int id);
	String getTenantStatusById(int id);
	
}

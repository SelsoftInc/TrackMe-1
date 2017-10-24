package com.selsoft.trackme.dao;

import com.selsoft.trackme.model.Lease;

public interface LeaseDAO {
	
	Object priorDataValidation(Lease lease);
	String getPropertyStatusById(int id);
}

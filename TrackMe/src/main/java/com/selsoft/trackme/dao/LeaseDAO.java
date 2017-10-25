package com.selsoft.trackme.dao;

import com.selsoft.trackme.model.Lease;

public interface LeaseDAO {

	String getPropertyStatusById(int id);

	String getTenantStatusById(int id);

	void createLease(Lease lease);

}

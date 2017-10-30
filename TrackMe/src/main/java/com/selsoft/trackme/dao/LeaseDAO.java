package com.selsoft.trackme.dao;

import java.util.Date;
import java.util.List;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;

public interface LeaseDAO {

	String getPropertyStatusById(int id);

	String getTenantStatusById(int id);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail,int propertyId);

	List<Property> fetchLeases(String propertyId);

	List<Property> getRentalDetail(String propertyId, Date effectiveDate);

	
}
	

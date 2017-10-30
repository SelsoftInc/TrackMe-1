package com.selsoft.trackme.dao;

import java.sql.Date;
import java.util.List;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;

public interface LeaseDAO {

	String getPropertyStatusById(int id);

	String getTenantStatusById(int id);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail,int propertyId);

	List<Property> fetchLeases(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId,Date inputDate);

	
}
	

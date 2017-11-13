package com.selsoft.property.dao;

import java.util.Date;
import java.util.List;

import com.selsoft.property.model.Lease;
import com.selsoft.property.model.RentalDetail;


public interface LeaseDAO {

	String getPropertyStatusById(int id);

	String getTenantStatusById(int id);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail, int propertyId);

	List<RentalDetail> getAllRentalDetails(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId, Date inputDate);

}

package com.selsoft.tenant.dao;

import java.util.Date;
import java.util.List;

import com.selsoft.tenant.model.Lease;
import com.selsoft.tenant.model.RentalDetail;

public interface LeaseDAO {

	String getPropertyStatusById(int id);
	String getTenantStatusById(int id);
	void createLease(Lease lease);
	void saveRentalDetail(RentalDetail rentalDetail, int propertyId);
	List<RentalDetail> getAllRentalDetails(Integer propertyId);
	RentalDetail getRentalDetail(Integer propertyId, Date inputDate);

}

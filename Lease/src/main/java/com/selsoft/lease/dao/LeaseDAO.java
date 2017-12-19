package com.selsoft.lease.dao;

import java.util.Date;
import java.util.List;

import com.selsoft.lease.model.Lease;
import com.selsoft.lease.model.RentalDetail;

public interface LeaseDAO {

	String getPropertyStatusById(int id);

	String getTenantStatusById(int id);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail, int propertyId);

	List<RentalDetail> getAllRentalDetails(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId, Date inputDate);

	String getFileNameById(String leaseId);

}

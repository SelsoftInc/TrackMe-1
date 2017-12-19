package com.selsoft.lease.service;

import java.util.List;

import com.selsoft.lease.model.Lease;
import com.selsoft.lease.model.RentalDetail;
import com.selsoft.lease.model.ValidError;

public interface LeaseService {

	ValidError validateNewLeaseData(Lease lease);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail, String propertyId);

	ValidError validateNewRentalData(RentalDetail rentalDetail);

	List<RentalDetail> getAllRentalDetails(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId, String inputDate);

	String getFileNameById(String leaseId);

}

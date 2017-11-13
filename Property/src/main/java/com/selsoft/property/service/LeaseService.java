package com.selsoft.property.service;

import java.util.List;

import com.selsoft.property.model.Lease;
import com.selsoft.property.model.RentalDetail;
import com.selsoft.property.model.ValidError;

public interface LeaseService {

	ValidError validateNewLeaseData(Lease lease);

	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail, String propertyId);

	ValidError validateNewRentalData(RentalDetail rentalDetail);

	List<RentalDetail> getAllRentalDetails(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId, String inputDate);

}

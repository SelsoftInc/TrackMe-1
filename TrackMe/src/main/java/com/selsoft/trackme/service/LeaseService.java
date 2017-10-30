package com.selsoft.trackme.service;

import java.util.Date;
import java.util.List;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.ValidError;

public interface LeaseService {

	ValidError validateNewLeaseData(Lease lease);


	void createLease(Lease lease);

	void saveRentalDetail(RentalDetail rentalDetail,String propertyId);
	
	ValidError validateNewRentalData(RentalDetail rentalDetail);
	
	List<Property> getAllRentalDetails(Integer propertyId);

	RentalDetail getRentalDetail(Integer propertyId,Date  inputDate);

}

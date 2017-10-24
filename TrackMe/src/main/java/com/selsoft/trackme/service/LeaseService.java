package com.selsoft.trackme.service;

import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.ValidError;

public interface LeaseService {

	ValidError priorDataValidation(Lease lease);

	String getPropertyStatusById(int id);

	ValidError createLease(Lease lease);

}

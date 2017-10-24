package com.selsoft.trackme.service;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Lease;

public interface LeaseService {
	

Errors priorDataValidation(Lease lease);

}

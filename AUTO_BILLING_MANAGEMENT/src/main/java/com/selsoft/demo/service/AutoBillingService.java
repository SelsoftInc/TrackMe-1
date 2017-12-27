package com.selsoft.demo.service;

import java.util.List;

import com.selsoft.demo.model.Lease;

public interface AutoBillingService {
	
	List<Lease> getAllRecords(String endDate);

}

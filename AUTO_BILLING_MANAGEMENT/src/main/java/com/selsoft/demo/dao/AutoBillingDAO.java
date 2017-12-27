package com.selsoft.demo.dao;

import java.util.List;
import com.selsoft.demo.model.Lease;



public interface AutoBillingDAO {

	List<Lease> getAllRecords(String endDate);

	}
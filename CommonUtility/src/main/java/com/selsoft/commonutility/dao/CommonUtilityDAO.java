package com.selsoft.commonutility.dao;

import java.util.List;

import com.selsoft.commonutility.model.CommonUtility;



public interface CommonUtilityDAO {

	List<CommonUtility> getCommonData( CommonUtility commonUtility);
	//List<CommonUtility> getDashboardDataForManager(String managerId);
	long activeOwners(String managerId);
	long totalNoOfProperties(String managerId);
	long totalNoOfActiveProperties(String managerId);
	long totalNoOfVacantProperties(String managerId);
	double totalRentCollected(String managerId,String paidOn,String transactionType,String transactionCode);
	double totalExpense(String managerId,String paidOn,String transactionType); 
}

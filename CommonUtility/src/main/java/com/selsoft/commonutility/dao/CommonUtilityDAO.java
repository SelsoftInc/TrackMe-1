package com.selsoft.commonutility.dao;

import java.util.List;
import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.utils.CommonUtilityException;

public interface CommonUtilityDAO {


	List<CommonUtility> getCommonData(CommonUtility commonUtility);

	// List<CommonUtility> getDashboardDataForManager(String managerId);
	long activeOwners(String managerId) throws CommonUtilityException;

	long totalNoOfProperties(String managerId) throws CommonUtilityException;

	long totalNoOfOccupiedProperties(String managerId) throws CommonUtilityException;

	long totalNoOfVacantProperties(String managerId) throws CommonUtilityException;

	double totalExpense(String managerId, String paidOn) throws CommonUtilityException;

	double totalRentCollected(String managerId, String paidOn) throws CommonUtilityException;
}

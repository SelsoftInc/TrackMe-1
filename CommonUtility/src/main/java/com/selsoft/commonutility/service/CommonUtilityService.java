package com.selsoft.commonutility.service;

import java.util.List;

import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.utils.CommonUtilityException;



public interface CommonUtilityService {

	List<CommonUtility> getCommonData(CommonUtility commonUtility);

	List<CommonUtility> getDashboardDataForManager(String managerId) throws CommonUtilityException;


}


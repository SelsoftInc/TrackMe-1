package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.CommonUtility;

public interface CommonUtilityService {

	List<CommonUtility> getCommonData();

	List<CommonUtility> getAllCombinationData(String[] module, String[] submodule, String[] code);

}

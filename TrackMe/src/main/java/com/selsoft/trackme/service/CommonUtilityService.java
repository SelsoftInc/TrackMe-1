package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.CommonUtility;

public interface CommonUtilityService {

	List<CommonUtility> getCommonData(Integer code);

	List<CommonUtility> getAllCombinationData(Integer module, Integer submodule, Integer code);

}

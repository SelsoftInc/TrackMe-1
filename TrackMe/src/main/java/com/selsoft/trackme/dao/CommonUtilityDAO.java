package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.CommonUtility;

public interface CommonUtilityDAO {

	List<CommonUtility> getCommonData(Integer code);
	
	public List<CommonUtility> getAllCombinationData(Integer module, Integer submodule, Integer code);

}

package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.CommonUtility;

public interface CommonUtilityDAO {

	List<CommonUtility> getCommonData();

	public List<CommonUtility> getAllCombinationData(String[] module, String[] submodule, String[] code);

}

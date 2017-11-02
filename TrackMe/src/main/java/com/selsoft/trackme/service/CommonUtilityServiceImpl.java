package com.selsoft.trackme.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.selsoft.trackme.dao.CommonUtilityDAO;
import com.selsoft.trackme.model.CommonUtility;

public class CommonUtilityServiceImpl implements CommonUtilityService {

	@Autowired
	private CommonUtilityDAO commonUtilityDAO;

	private static final Logger logger = Logger.getLogger(CommonUtilityServiceImpl.class);
	@Override
	public List<CommonUtility> getCommonData(Integer code) {
		
		return commonUtilityDAO.getCommonData(code);
		
	}
	@Override
	public List<CommonUtility> getAllCombinationData(Integer module, Integer submodule, Integer code) {
		return commonUtilityDAO.getAllCombinationData(module,submodule,code);
		
	}

}

package com.selsoft.trackme.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.CommonUtilityDAO;
import com.selsoft.trackme.model.CommonUtility;

@Service
public class CommonUtilityServiceImpl implements CommonUtilityService {

	@Autowired
	private CommonUtilityDAO commonUtilityDAO;

	private static final Logger logger = Logger.getLogger(CommonUtilityServiceImpl.class);
	@Override
	public List<CommonUtility> getCommonData(CommonUtility commonUtility) {
		
		return commonUtilityDAO.getCommonData(commonUtility);
		
	}

}


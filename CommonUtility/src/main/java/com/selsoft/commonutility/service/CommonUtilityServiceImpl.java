package com.selsoft.commonutility.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.selsoft.commonutility.dao.CommonUtilityDAO;
import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.model.Property;
import com.selsoft.commonutility.model.Transaction;
import com.selsoft.commonutility.utils.CommonUtilityException;

@Service
public class CommonUtilityServiceImpl implements CommonUtilityService {

	@Autowired
	private CommonUtilityDAO commonUtilityDAO;

	private static final Logger logger = Logger.getLogger(CommonUtilityServiceImpl.class);

	@Override
	public List<CommonUtility> getCommonData(CommonUtility commonUtility) {

		return commonUtilityDAO.getCommonData(commonUtility);

	}

	@Override
	public List<CommonUtility> getDashboardDataForManager(String managerId) throws CommonUtilityException {
		String paidOn = null;
		String transactionType = null;
		String transactionCode = null;
		long ownerList = commonUtilityDAO.activeOwners(managerId);
		long propertyList = commonUtilityDAO.totalNoOfActiveProperties(managerId);
		long propList = commonUtilityDAO.totalNoOfProperties(managerId);
		long propsList = commonUtilityDAO.totalNoOfVacantProperties(managerId);
		double transactionList = commonUtilityDAO.totalRentCollected(managerId, paidOn, transactionType,
				transactionCode);
		double trans = commonUtilityDAO.totalExpense(managerId, paidOn, transactionType);
		return null;
	}
}

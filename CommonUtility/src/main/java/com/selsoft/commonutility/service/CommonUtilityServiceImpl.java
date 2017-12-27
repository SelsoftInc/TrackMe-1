package com.selsoft.commonutility.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONObject;
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
	public JSONObject getDashboardDataForManager(String managerId) {
		String year = null;
		JSONObject jsonObject = new JSONObject();
		List<Error> errorList = new ArrayList<Error>();

		try {
			long activeOwners = commonUtilityDAO.activeOwners(managerId);
			long totalProperties = commonUtilityDAO.totalNoOfProperties(managerId);
			long occupiedProperties = commonUtilityDAO.totalNoOfOccupiedProperties(managerId);
			long vacantProperties = commonUtilityDAO.totalNoOfVacantProperties(managerId);
			double totalRent = commonUtilityDAO.totalRentCollected(managerId, year);
			double totalExpenses = commonUtilityDAO.totalExpense(managerId, year);

			jsonObject.put("success", true);
			jsonObject.put("activeOwners", activeOwners);
			jsonObject.put("totalProperties", totalProperties);
			jsonObject.put("occupiedProperties", occupiedProperties);
			jsonObject.put("vacantProperties", vacantProperties);
			jsonObject.put("totalRentCollectedTillDate", totalRent);
			jsonObject.put("totalExpenseTillDate", totalExpenses);

		} catch (CommonUtilityException e) {
			errorList.add(new Error(e));
			jsonObject.put("error", errorList);
			jsonObject.put("success", false);
		} catch (Throwable t) {
			errorList.add(new Error(new CommonUtilityException("Fatal", t)));
			jsonObject.put("success", false);
			// jsonObject.put("errorType", Error);
			jsonObject.put("error", new Error(new CommonUtilityException("Fatal", t)));
		}

		return jsonObject;
	}
}

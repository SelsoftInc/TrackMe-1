package com.selsoft.commonutility.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.service.CommonUtilityService;
import com.selsoft.commonutility.utils.CommonUtilityException;


@SuppressWarnings({ "unused" })
@RestController
@RequestMapping(value = "/common")
public class CommonUtilityController {
	private static final Logger logger = Logger.getLogger(CommonUtilityController.class);
	@Autowired
	private CommonUtilityService commonUtilityService;

	@RequestMapping(value = "getCommonData", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommonUtility> getCommonData(@RequestBody CommonUtility commonUtility) {
		return commonUtilityService.getCommonData(commonUtility);
	}

	@RequestMapping(value = "/getDashboardDataForManager/{managerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDashboardDataForManager(@PathVariable("managerId") String managerId) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		List<CommonUtility> commonUtility = null;
		List<Error> errorList = new ArrayList<Error>();
		
		try {
			commonUtility = commonUtilityService.getDashboardDataForManager(managerId);
			jsonObject.put("success", true);
			jsonObject.put("activeOwners", 7);
			jsonObject.put("totalProperties", 13);
			jsonObject.put("occupiedProperties", 9);
			jsonObject.put("vacantProperties", 4);
			jsonObject.put("totalRentCollectedTillDate", 21600);
			jsonObject.put("totalExpenseTillDate", 1325.34);
			} catch(CommonUtilityException e) {
			errorList.add(new Error(e));
			jsonObject.put("error", errorList);
			jsonObject.put("success", false);
		} catch(Throwable t) {
			errorList.add(new Error(new CommonUtilityException("Fatal", t)));
			jsonObject.put("success", false);
			//jsonObject.put("errorType", Error);
			jsonObject.put("error", new Error(new CommonUtilityException("Fatal", t)));
		}
		
		return jsonObject.toString(); 
	}
		
}
